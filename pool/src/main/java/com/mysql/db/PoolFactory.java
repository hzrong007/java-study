package com.mysql.db;

public class PoolFactory implements Pool {
    public static ConnectPool getInstance() {
        return DBPool.connectPool;
    }

    static class DBPool {
        private static ConnectPool connectPool;

        static {
            try {
                connectPool = new ConnectPool(new DataSource()).init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
