package com.rw.spi.dbDriver;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

public class JdbcSpiExample {

    public static void main(String[] args) {
        ServiceLoader<Driver> drivers = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = drivers.iterator();
        while (iterator.hasNext()) {
            Driver next = iterator.next();
            System.out.println(next.getClass().getName());
        }
    }
}
