package com.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class CgExample {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloInterfaceImpl.class);
        CgProxy cgProxy = new CgProxy();
        enhancer.setCallback(cgProxy);
        HelloInterfaceImpl helloInterface = (HelloInterfaceImpl) enhancer.create();
        helloInterface.sayHello();
    }
}
