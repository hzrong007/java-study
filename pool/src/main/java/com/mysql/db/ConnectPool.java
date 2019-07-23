package com.mysql.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectPool {
    private static Logger logger = LoggerFactory.getLogger(ConnectPool.class);

    private Vector<ConnectChannel> vector = new Vector<>();
    private DataSource dataSource;

    public ConnectPool(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ConnectPool init() throws Exception {
        createConnects(this.dataSource.getCoreNum());
        return this;
    }

    private synchronized void createConnects(int count) throws Exception {
        Class.forName(dataSource.getDriver());
        if (vector.size() > dataSource.getMaxNum() || (vector.size() + count > dataSource.getMaxNum())) {
            logger.error("已达到最大连接数");
            throw new Exception("已达到最大连接数");
        }
        for (int i = 0; i < count; i++) {
            Connection connection = createConnection();
            ConnectChannel connectChannel = new ConnectChannel(connection, false);
            vector.add(connectChannel);
        }
    }

    public synchronized ConnectChannel getPooledConnect() throws Exception {
        ConnectChannel connectChannel = getConnectChannel();
        if (connectChannel == null && !isFull()) {
            createConnects(1);
            connectChannel = getConnectChannel();
        }
        return connectChannel;
    }

    private synchronized ConnectChannel getConnectChannel() throws SQLException {
        for (ConnectChannel channel : vector) {
            if (!channel.isLock()) {
                if (!isValid(channel)) {
                    Connection connection = createConnection();
                    channel.setConnection(connection);
                }
                channel.setLock(true);
                return channel;
            }
        }
        return null;
    }

    private boolean isFull() {
        return vector.size() >= dataSource.getMaxNum();
    }

    private boolean isValid(ConnectChannel channel) {
        boolean valid;
        try {
            valid = channel.getConnection().isValid(dataSource.getTimeout());
        } catch (SQLException e) {
            valid = false;
        }
        return valid;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(dataSource.getUrl(), dataSource.getUser(), dataSource.getPassword());
    }
}
