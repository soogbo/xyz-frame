package xyz.java.main.datastructure;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;

/**
 * interface ConcurrentMap<K, V> extends Map<K, V>
 * 
 * @author shisp
 * @date 2018-12-19 19:53:53
 */
public class ConcurrentMapDemo {

    public static void main(String[] args) {

        putIfAbsent();
        getOrDefault();
        ConcurrentHashMap();
    }

    /**
     * 三种类型的并行操作：forEach、search 和 reduce.类似于并行流，并行线程池默认为CPU核心数，JVM可定义
     * 
     * 方法的第一个参数是通用的parallelismThreshold。这一阈值表示操作并行执行时的最小集合大小。例如，如果你传入阈值500，而映射的实际大小是499，那么操作就会在单线程上串行执行
     * 
     */
    private static void ConcurrentHashMap() {
        System.out.println("CPU核心数：" + ForkJoinPool.getCommonPoolParallelism());
        
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        
        //1 foreach
        map.forEach(1, (key, value) ->
        System.out.printf("key: %s; value: %s; thread: %s\n",
            key, value, Thread.currentThread().getName()));
        
        
        //2 search：找到第一个后就停止，map是无序的
        String result = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if ("foo".equals(key)) {
                return value;
            }
            return null;
        });
        System.out.println("Result: " + result);

        
        //3 reduce
        String reduce = map.reduce(1,
                (key, value) -> {
                    System.out.println("Transform: " + Thread.currentThread().getName());
                    return key + "=" + value;
                },
                (s1, s2) -> {
                    System.out.println("Reduce: " + Thread.currentThread().getName());
                    return s1 + ", " + s2;
                });

        System.out.println("Result: " + reduce);
        
    }
    
    
    

    /**
     * putIfAbsent:key不存在时添加到map中并返回NULL，存在时不添加返回存在的value
     */
    private static void putIfAbsent() {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        
        String value = map.putIfAbsent("c3", "p0");
        System.out.println("key不存在时添加到map中并返回null:" + value);
        String value2 = map.putIfAbsent("c3", "p1");
        System.out.println("key已存在，不会修改map中原始值并返回map中原始值:" + value2);
    }
    
    /**
     * getOrDefault：key不存在时返回默认值
     */
    private static void getOrDefault() {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        
        String value = map.getOrDefault("hi", "我是默认值");
        System.out.println("key不存在时返回默认值" + value); 
    }

}
