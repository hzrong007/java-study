package com.mysql.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectChannel {
    private static Logger logger = LoggerFactory.getLogger(ConnectChannel.class);
    private Connection connection;
    private Boolean lock;

    public ConnectChannel() {
    }

    public ConnectChannel(Connection connection, Boolean lock) {
        this.connection = connection;
        this.lock = lock;
    }

    public void query(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        logger.info("SQL={}, result={}", sql, resultSet.wasNull());
        resultSet.close();
        statement.close();
    }

    public void close() {
        this.lock = false;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public boolean isLock() {
        return lock != null ? lock : true;
    }
}
