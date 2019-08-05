package com.stream.funcationinterface;

@FunctionalInterface
public interface PredicateInterfaceExample<T> {

    boolean invoke(T t);
}
