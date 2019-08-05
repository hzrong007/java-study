package com.stream;

import com.google.common.base.Strings;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreamTest {
    @Test
    public void testParallelRate() {
        int max = Integer.MAX_VALUE;
        IntStream stream = IntStream.rangeClosed(0, max);
        long startTime = System.nanoTime();
        int sum = stream.sum();
        long endTime = System.nanoTime();
        System.out.println("sum: " + sum);
        System.out.println("时间差：" + (endTime - startTime));

        IntStream stream2 = IntStream.rangeClosed(0, max);
        startTime = System.nanoTime();
        sum = stream2.parallel().sum();
        endTime = System.nanoTime();
        System.out.println("parallel sum: " + sum);
        System.out.println("parallel 时间差：" + (endTime - startTime));
    }

    @Test
    public void testParallelInt2() {
        List<Integer> list1 = new ArrayList<>();
        IntStream.range(1, 10000).forEach(list1::add);
        System.out.println("list1 size: " + list1.size());
        List<Integer> list2 = new ArrayList<>();
        IntStream.range(1, 10000).parallel().forEach(list2::add);
        System.out.println("list2 size: " + list2.size());
    }

    @Test
    public void testParallelInt3() {
        Vector<Integer> list1 = new Vector<>();
        IntStream stream = IntStream.range(1, 10000);
        long startTime = System.nanoTime();
        stream.forEach(list1::add);
        long endTime = System.nanoTime();
        System.out.println("list1 size: " + list1.size());
        System.out.println("list1 时间差: " + (endTime - startTime));

        Vector<Integer> list2 = new Vector<>();
        IntStream parallel = IntStream.range(1, 10000).parallel();
        startTime = System.nanoTime();
        parallel.forEach(list2::add);
        endTime = System.nanoTime();
        System.out.println("list2 size: " + list2.size());
        System.out.println("list2 时间差: " + (endTime - startTime));
    }

    @Test
    public void testParallelString() {
        List<String> list = Arrays.asList("阿斯利康的", "奥视频大门票", "asdasd123");
        List<String> collect = list.parallelStream().map(v -> Strings.repeat(v, 2)).collect(Collectors.toList());
        System.out.println(collect);
    }
}
