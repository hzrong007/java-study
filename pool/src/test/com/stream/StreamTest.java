package com.stream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    private static Logger logger = LoggerFactory.getLogger(StreamTest.class);

    @Test
    public void testFilter() {
        List<String> colors = new ArrayList<>(8);
        colors.add("red");
        colors.add("yellow");
        colors.add("blue");
        List<String> collect = colors.stream().filter(a -> a.equals("blue")).collect(Collectors.toList());
        logger.info("filter: {}", collect);
    }

    @Test
    public void testForeach() {
        Random random = new Random(10);
        random.ints().limit(3).forEach(System.out::println);
    }

    @Test
    public void testMap() {
        List<Integer> nums = new ArrayList<>(8);
        nums.add(1);
        nums.add(2);
        nums.add(3);
        Stream<Integer> integerStream = nums.stream().map(e -> e + 1);
        System.out.println(integerStream.collect(Collectors.toList()));
    }

    @Test
    public void testDistinct() {
        List<Integer> nums = new ArrayList<>(8);
        nums.add(-2);
        nums.add(-1);
        nums.add(-1);
        nums.add(1);
        nums.add(2);
        List<Integer> collect = nums.stream().map(Math::abs).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void testSorted() {
        List<Integer> nums = new ArrayList<>(8);
        nums.add(-2);
        nums.add(-1);
        nums.add(-1);
        nums.add(1);
        nums.add(2);
        List<Integer> collect = nums.stream().sorted().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void testSorted2() {
        List<StreamVO> list = new ArrayList<>(4);
        list.add(new StreamVO("tom", 25));
        list.add(new StreamVO("lily", 20));
        list.add(new StreamVO("jack", 25));
        List<StreamVO> collect = list.stream().sorted(Comparator.comparing(StreamVO::getAge)).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(list);
    }

    @Test
    public void testMapToInt() {
        List<Integer> integers = Arrays.asList(1, 2, 10);
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }

    @Test
    public void testSummaryStats() {
        List<Integer> integers = Arrays.asList(1, 2, 10);
        IntSummaryStatistics intSummaryStatistics = integers.stream().mapToInt(Integer::intValue).summaryStatistics();
        System.out.println("min: " + intSummaryStatistics.getMin());
        System.out.println("max: " + intSummaryStatistics.getMax());
        System.out.println("sum: " + intSummaryStatistics.getSum());
        System.out.println("count: " + intSummaryStatistics.getCount());
        System.out.println("average: " + intSummaryStatistics.getAverage());
    }

    @Test
    public void testReduce() {
        List<String> colors = new ArrayList<>(8);
        colors.add("red");
        colors.add("blue");
        colors.add("yellow");
        String s = colors.stream().reduce(BinaryOperator.maxBy(String::compareTo)).get();
        System.out.println("max: " + s);
        s = colors.stream().reduce(BinaryOperator.minBy(String::compareTo)).get();
        System.out.println("min: " + s);
    }

    @Test
    public void testFlatMap() {
        Stream<List<String>> stream = Stream.of(Arrays.asList("1", "2"), Arrays.asList("3", "4", "5"));
        List<String> collect = stream.flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void testSkipAndLimit() {
        IntStream intStream = IntStream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        intStream.skip(2).limit(5).forEach(System.out::println);
    }

    @Test
    public void testLimitAndSkip() {
        IntStream intStream = IntStream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        intStream.limit(5).skip(2).forEach(System.out::println);
    }

    @Test
    public void testGroupBy() {
        Map<Integer, List<Integer>> map1 = Stream.of(1, 3, 3, 4).collect(Collectors.groupingBy(x -> x));
        Map<Integer, Long> map2 = Stream.of(1, 3, 3, 4).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        HashMap<Integer, Long> hashMap = Stream.of(1, 3, 3, 4).collect(Collectors.groupingBy(x -> x, HashMap::new, Collectors.counting()));
        System.out.println(map1);
        System.out.println(map2);
        System.out.println(hashMap);
    }

    @Test
    public void testPartitioningBy() {
        Map<Boolean, List<Integer>> collect1 = Stream.of(1, 3, 3, 4).collect(Collectors.partitioningBy(x -> x % 2 == 0));
        System.out.println(collect1);
        Map<Boolean, Long> collect = Stream.of(1, 3, 3, 4).collect(Collectors.partitioningBy(x -> x % 2 == 0, Collectors.counting()));
        System.out.println(collect);
    }

    @Test
    public void testToCollection() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
        ArrayList<Integer> collect = integerStream.collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collect);
    }

    @Test
    public void testToCollectionMap() {
        Stream<StreamVO> stream = Stream.of(
                new StreamVO("tom", 10),
                new StreamVO("lily", 9),
//                new StreamVO("lucy", 10),
                new StreamVO("John", 12)
        );
        ConcurrentMap<Integer, StreamVO> collect = stream.collect(Collectors.toConcurrentMap(StreamVO::getAge, s -> s));
        System.out.println(collect);

        // 如果KEY相同，就会报错：java.lang.IllegalStateException: Duplicate key
    }

    @Test
    public void testToMap() {
        Stream<StreamVO> stream = Stream.of(
                new StreamVO("tom", 10),
                new StreamVO("lily", 9),
                new StreamVO("lucy", 10),
                new StreamVO("John", 12)
        );
        Map<Integer, List<StreamVO>> collect = stream.collect(Collectors.groupingBy(StreamVO::getAge, Collectors.toList()));
        System.out.println(collect);
    }
}
