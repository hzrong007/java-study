package com.rw.common;

import org.junit.Assert;
import org.junit.Test;
import sun.reflect.Reflection;

public class ReflectionTest {

    /**
     * 0 和小于0  -   返回 Reflection类
     * 1  -   返回自己的类
     * 2  -    返回调用者的类
     * 3. 4. ....层层上传。
     */
    @Test
    public void getCallerClass0() {
        System.out.println(Reflection.getCallerClass(0));
        System.out.println(Reflection.getCallerClass(1));
        System.out.println(Reflection.getCallerClass(2));
    }

    @Test
    public void getCallerClass() {
        try {
            System.out.println(Reflection.getCallerClass());
            Assert.fail("未申明，不可反射");
        } catch (InternalError e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 申明CallerSensitive，
     * 但未设置BootStrap加载的用户代码路径
     */
    @Test
    public void execStudentCallerSensitiveFail() {
        try {
            Student.exec();
            Assert.fail("申明，未设置");
        } catch (InternalError e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 申明CallerSensitive，
     * 设置BootStrap加载的用户代码路径
     * VM options: -Xbootclasspath/a:D:\workspaces\daydayup\java-study\common\target\classes
     */
    @Test
    public void execStudentCallerSensitiveSuccess() {
        Student.exec();
        System.out.println("申明，已设置BootStrap加载路径");
    }
}
