package com.proxy.jdk;

public class HelloInterfaceImpl implements HelloInterface {

    @Override
    public String sayHello() {
        System.out.println("Hello");
        return System.currentTimeMillis() + "";
    }
}
