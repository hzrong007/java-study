package com.stream.funcationinterface;

import org.junit.Test;

import java.util.regex.Pattern;

public class FunctionInterfaceTest {

    @Test
    public void testFuncInterface() {
        FunctionInterfaceExample<Integer, Integer> func = x -> x + 1;
        System.out.println(func.invoke(1));
    }

    @Test
    public void testConsumerInterface() {
        ConsumerInterfaceExample<Integer> func = System.out::println;
        func.invoke(2);
    }

    @Test
    public void testSupplierInterface() {
        SupplierInterfaceExample<Long> func = System::currentTimeMillis;
        System.out.println(func.invoke());
    }

    @Test
    public void testPredicateInterface() {
        PredicateInterfaceExample<String> func = x -> Pattern.matches("\\w{2,}", x);
        System.out.println(func.invoke("sad"));
    }
}
