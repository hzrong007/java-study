package com.stream.funcationinterface;

@FunctionalInterface
public interface ConsumerInterfaceExample<T> {

    void invoke(T t);
}
