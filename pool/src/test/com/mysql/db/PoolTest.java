package com.mysql.db;

import org.junit.Test;

public class PoolTest {

    @Test
    public void testPool() throws Exception {
        ConnectPool pool = PoolFactory.getInstance();
        ConnectChannel pooledConnect = pool.getPooledConnect();
        pooledConnect.query("SELECT * from user_info WHERE cert_no='370126199807226275';");
        pooledConnect.close();
    }
}
