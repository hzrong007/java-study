package com.mysql.db;

import com.mysql.db.ConnectPool;
import com.mysql.db.PoolFactory;

public class PoolTest {

    public static void main(String[] args) throws Exception {
        ConnectPool pool = PoolFactory.getInstance();
        pool.getPooledConnect().query("SELECT * from user_info WHERE cert_no='370126199807226275';");
    }
}
