package com.rw.common;

import org.junit.Assert;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeTest {

    @Test
    public void testGetUnSafeFail() {
        try {
            Unsafe.getUnsafe();
            Assert.fail("UnSafe");
        } catch (SecurityException se) {
        }
    }

    @Test
    public void testGetUnSafeSuccess() throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        // 获取值
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        System.out.println(unsafe);
        System.out.println(unsafe.addressSize());
    }

}
