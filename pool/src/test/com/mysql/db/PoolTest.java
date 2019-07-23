package com.mysql.db;

public class PoolTest {

    public static void main(String[] args) throws Exception {
        ConnectPool pool = PoolFactory.getInstance();
        ConnectChannel pooledConnect = pool.getPooledConnect();
        pooledConnect.query("SELECT * from user_info WHERE cert_no='370126199807226275';");
        pooledConnect.close();
    }
}
