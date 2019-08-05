package com.stream.funcationinterface;

@FunctionalInterface
public interface FunctionInterfaceExample<T, R> {

    R invoke(T x);
}
