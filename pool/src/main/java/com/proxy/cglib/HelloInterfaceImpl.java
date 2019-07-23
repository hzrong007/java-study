package com.proxy.cglib;

public class HelloInterfaceImpl {

    public String sayHello() {
        System.out.println("Hello");
        return System.currentTimeMillis() + "";
    }
}
