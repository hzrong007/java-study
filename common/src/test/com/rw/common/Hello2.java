package com.rw.common;

public class Hello2 extends Hello1 {

    @Override
    public void print() {
        System.out.println("Hello2");
    }

    public static void main(String[] args) {
        Hello2 hello2 = new Hello2();
        hello2.print2();
    }
}
