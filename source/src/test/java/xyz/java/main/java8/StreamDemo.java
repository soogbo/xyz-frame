package xyz.java.main.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 数据流
 * 
 * @author shisp
 * @date 2018-12-14 17:58:55
 */
public class StreamDemo {

    private static List<String> stringCollection = new ArrayList<>();
    
    static{
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc2");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");  
    }
    
    public static void main(String[] args) {
        filter();
        sorted();
        map();
        count();
        reduce();
        match();
    }
    
    /**
     * Filter：(中间操作)接受一个predicate接口类型的变量，并将所有流对象中的元素进行过滤
     */
    static void filter() {
        System.out.println("filter=================");
        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::print);
        System.out.println();
        // aaa2aaa1
    }
    
    /**
     * Sorted：(中间操作)返回一个排过序的流对象的视图，默认自然排序，或手动添加Comparator接口，sorted只是创建一个流对象排序的视图，而不会改变原来集合中元素的顺序
     * parallelStream：并行排序，开启多线程
     */
    static void sorted() {
        System.out.println("sorted=================");
        stringCollection.stream().sorted().forEach(System.out::print);
        stringCollection.stream().sorted((a,b)-> a.length()-b.length()).forEach(System.out::print);
        stringCollection.stream().sorted(StreamDemo::comp).forEach(System.out::print);
        //parallelStream：并行排序，开启多线程
        stringCollection.parallelStream().sorted(StreamDemo::comp).forEach(System.out::print);
        
        System.out.println();
        // aaa1aaa2bbb1bbb2bbb3ccc2ddd1
    }
    
    static int comp(String a,String b) {
        return a.length() - b.length();
    }
    
    /**
     * Map：(中间操作)通过给定的方法，它能够把流对象中的每一个元素对应到另外一个对象上
     */
    static void map() {
        System.out.println("map=================");
        stringCollection.stream().map(s -> Integer.parseInt(s.substring(3, 4))).forEach(System.out::print);
        // 221132218
        System.out.println();
    }
    
    /**
     * Count：(终结操作)返回当前流对象中包含的元素数量
     */
    static void count() {
        System.out.println("count=================");
        long count = stringCollection.stream().count();
        System.out.println(count);
        // stringCollection.size()
    }
    
    /**
     * Reduce：(终结操作)通过某一个方法，对元素进行削减操作，结果会放在一个Optional变量里返回
     */
    static void reduce() {
        System.out.println("reduce=================");
        Optional<String> reduced = stringCollection
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
        // aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2
    }
    
    /**
     * 
     * Match：(终结操作)有多种不同的类型，都是用来判断某一种规则是否与流对象相互吻合的，只返回一个boolean类型的结果
     */
    static void match() {
        System.out.println("match=================");
        boolean anyMatch = stringCollection
                .stream()
                .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyMatch);
        
        boolean anyMatch1 = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyMatch1);
        boolean anyMatch2 = stringCollection.stream().allMatch((s) -> s.startsWith("a"));
        System.out.println(anyMatch2);
        boolean anyMatch3 = stringCollection.stream().noneMatch((s) -> s.startsWith("a"));
        System.out.println(anyMatch3);
    }
}
