package com.proxy.jdk;

import java.lang.reflect.Proxy;

public class JDKExample {

    public static void main(String[] args) {
        HelloInterface helloInterface = new HelloInterfaceImpl();
        JdkProxy jdkProxy = new JdkProxy(helloInterface);
        HelloInterface helloProxy = (HelloInterface) Proxy.newProxyInstance(jdkProxy.getClass().getClassLoader(), helloInterface.getClass().getInterfaces(), jdkProxy);
        helloProxy.sayHello();

        // 测试不使用接口是否报错
/*        HelloInterfaceImpl helloInterface2 = new HelloInterfaceImpl();
        JdkProxy jdkProxy2 = new JdkProxy(helloInterface2);
        HelloInterfaceImpl helloProxy2 = (HelloInterfaceImpl) Proxy.newProxyInstance(jdkProxy2.getClass().getClassLoader(), helloInterface2.getClass().getInterfaces(), jdkProxy2);
        helloProxy2.sayHello();*/
        // 结果报错：java.lang.ClassCastException: com.sun.proxy.$Proxy0 cannot be cast to com.proxy.jdk.HelloInterfaceImpl
    }
}
