package com.rw.common;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

public class Student {

    @CallerSensitive
    public static void exec() {
        System.out.println("student exec: " + Reflection.getCallerClass());
    }

    public static void main(String[] args) {
        Student.exec();
    }
}
