package com.proxy.jdk;

import java.lang.reflect.Proxy;

public class JDKExample {

    public static void main(String[] args) {
        HelloInterface helloInterface = new HelloInterfaceImpl();
        JdkProxy jdkProxy = new JdkProxy(helloInterface);
        HelloInterface helloProxy = (HelloInterface) Proxy.newProxyInstance(jdkProxy.getClass().getClassLoader(), helloInterface.getClass().getInterfaces(), jdkProxy);
        helloProxy.sayHello();
    }
}
