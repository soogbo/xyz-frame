package xyz.java.main.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

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
        basicStream();
        specialStream();
        filter();
        sorted();
        count();
        reduce();
        match();
        collect();
        construcCollect();
        map();
        flatMap();
        filterByObjField();
    }

    /**
     * 基本数据流
     */
    static void basicStream() {
        System.out.println("basicStream=================");
        Arrays.asList("a1", "a2", "b3", "a0").stream().sorted().forEach(System.out::println);
        Stream.of("a1", "a2", "b3", "a0").forEach(System.out::println);
        Arrays.stream(new String[] { "a1", "a2", "b3", "a0" }).forEach(System.out::println);
        Arrays.stream(new String[] { "a1", "a2", "b3", "a0" }, 0, 2).forEach(System.out::println);
        
        int sum = Arrays.stream(new int[] { 1, 2, 3, 0 }, 0, 2).sum();
        System.out.println("基本数据流,使用聚合函数，sum=" + sum);
        OptionalDouble optionalDouble = Arrays.stream(new int[] { 1, 2, 3, 0 }, 0, 2).average();
        if (optionalDouble.isPresent()) {
            System.out.println("基本数据流,使用聚合函数，average=" + optionalDouble.getAsDouble());
        }
        
        System.out.println("stream供应器终止操作后就会关闭流，不可复用！");
        System.out.println("构造一个stream供应器，类似实例工厂！");
        Supplier<Stream<String>> streamSupplier = ()-> Stream.of("a1", "a2", "b3", "a0");
        Stream<String> s = streamSupplier.get();
        s.sorted().forEach(System.out::println);
       
    }
    
    /**
     * 特殊数据流：IntStream、LongStream、DoubleStream
     * 使用对应的IntFunction，IntPredicate
     * 特殊数据流与基本数据流相互转换
     */
    static void specialStream() {
        System.out.println("specialStream=================");
        IntStream.range(44, 48).forEach(System.out::println);
        long sum = LongStream.range(44L, 48L).sum();
        System.out.println("特殊数据流,使用聚合函数，sum=" + sum);
        List<String> list = LongStream.range(44L, 48L).mapToObj(String::valueOf).collect(Collectors.toList());
        System.out.println("特殊数据流与基本数据流相互转换：list=" + list);
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
        stringCollection.stream().sorted((a,b)-> a.length()-b.length()).forEach(System.out::print);
        stringCollection.stream().parallel();
        System.out.println();
        stringCollection.parallelStream().sorted((a,b)-> a.length()-b.length()).forEach(System.out::print);
        System.out.println();
        System.out.println("Arrays.parallelSort,并行流中排序会默认调用");
    }
    
    static int comp(String a,String b) {
        return a.length() - b.length();
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
        System.out.println("reduce1参数=BinaryOperator<T> accumulator================");
        System.out.println("参数：组合器函数：削减规约的函数");
        Optional<String> reduced = stringCollection
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
        // aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2
        
        System.out.println("reduce2=归约一个列表获取对象，返回一个新对象===============");
        System.out.println("reduce2参数=T identity, BinaryOperator<T> accumulator================");
        System.out.println("参数1：初始值：规约提供的的默认值对象，规约后的值返回的是创建的新对象");
        System.out.println("参数2：组合器函数：削减规约的函数");
        String reduced2 = stringCollection
                .stream()
                .sorted()
                .reduce("默认值", (s1, s2) -> s1 + "#" + s2);

        System.out.println(reduced2);
        System.out.println("reduce3=规约列表并返回其他格式的对象值=比如：操作person列表，返回计算的person列表的年龄之和，返回计算的字符串列表各元素长度之和===============");
        System.out.println("reduce3参数=U identity,BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner================");
        System.out.println("参数1：初始值：任意类型默认值，削减规约后返回此类型对象");
        System.out.println("参数2：BiFunction累加器，初始值与规约后对象的处理");
        System.out.println("参数3：组合器函数：削减规约的函数,组合对象是初始值对象");
        System.out.println(stringCollection);
        Integer reduce3 = stringCollection
                .stream()
                .reduce(0, (data, s) -> data+=s.length(),(data1, data2)-> data1+data2);
        System.out.println(reduce3);
        
    }
    
    /**
     * 
     * Match：(终结操作)有多种不同的类型，接受Predicate对象，都是用来判断某一种规则是否与流对象相互吻合的，只返回一个boolean类型的结果
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
    
    /**
     * 
     * (终结操作)将流中的元素存放在不同类型的结果中，接受收集器Collector；
     * collect接受收集器（Collector），
     * 它由四个不同的操作组成：供应器（supplier）、累加器（accumulator）、组合器（combiner）和终止器（finisher）
     * Java8通过内置的Collectors类支持多种内置的收集器。
     * 收集器（Collector）：构造列表，分组，聚合，字符串拼接；
     */
    private static void collect() {
        System.out.println("收集器Collector操作。。。");
        List<Person> persons = Arrays.asList(new Person("p1", 34), new Person("p2", 25));
        
        System.out.println("1.collect构造列表");
        List<Person> filtered =
                persons
                    .stream()
                    .filter(p -> p.getName().startsWith("p"))
                    .collect(Collectors.toList());
        System.out.println(filtered);
        
        System.out.println("2.collect分组");
        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.getAge()));

            personsByAge
                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
            
        System.out.println("3.collect聚合，计算年龄平均值");
        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.getAge()));

        System.out.println(averageAge);        
       
        System.out.println("4.collect聚合，年龄统计概要：IntSummaryStatistics{count=2, sum=59, min=25, average=29.500000, max=34}");
        IntSummaryStatistics ageSummary =
                persons
                    .stream()
                    .collect(Collectors.summarizingInt(p -> p.getAge()));

        System.out.println(ageSummary);
        
        System.out.println("4.collect聚合，拼接 ");
        String phrase = persons
                .stream()
                .filter(p -> p.getAge() >= 20)
                .map(p -> p.getName())
                .collect(Collectors.joining(" 2拼接内容(比如，) ", "字符串起始内容1 ", " 3拼接结束值."));

        System.out.println(phrase);
        System.out.println("字符串起始内容1 p1 2拼接内容(比如，) p2 3拼接结束值.");
        System.out.println("p1 p2是拼接进去的");
    }
    
    /**
     * 自定义收集器Collector
     * 四个不同的操作组成：供应器（supplier）、累加器（accumulator）、组合器（combiner）和终止器（finisher）
     */
    private static void construcCollect() {
        System.out.println("自定义收集器Collector。。。");
        List<Person> persons = Arrays.asList(new Person("p1", 34), new Person("p2", 25));

        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                    () -> new StringJoiner(" | "),          // supplier
                    (j, p) -> j.add(p.getName().toUpperCase()),  // accumulator
                    (j1, j2) -> j1.merge(j2),               // combiner
                    StringJoiner::toString);                // finisher
    
        String names = persons
            .stream()
            .collect(personNameCollector);
        System.out.println(names);
    }
    
    /**
     * Map：(中间操作)通过给定的方法，接收function对象，它能够把流对象中的每一个元素对应到另外一个对象上
     */
    static void map() {
        System.out.println("map=================");
        stringCollection.stream().map(s -> Integer.parseInt(s.substring(3, 4))).forEach(System.out::print);
        // 221132218
        System.out.println();
    }
    
    /**
     * flatMap：(中间操作)将流中的每个元素，转换为其它对象的流
     */
    private static void flatMap() {
        System.out.println("flatMap=================");
        System.out.println("把几个list或几个对象中的list转换为流，输出合并到一个流中");
        List<Person> aa = Arrays.asList(new Person("p1", 11), new Person("p2", 22));
        List<Person> bb = Arrays.asList(new Person("p3", 33), new Person("p4", 44));
        List<Person> cc = Arrays.asList(new Person("p5", 55), new Person("p6", 66));
        
        List<List<Person>> oo = new ArrayList<List<Person>>() {
            private static final long serialVersionUID = 1L;
            {
                add(aa);
                add(bb);
                add(cc);
            }
        };
        
        List<Person> list = oo.stream().flatMap(z -> z.stream()).collect(Collectors.toList());
        System.out.println(list);
        
    }
    
    /**
     * filter根据对象某属性去重，并返回map，key为属性值，value为对象
     */
    private static void filterByObjField() {
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        // null
        System.out.println(concurrentHashMap.putIfAbsent("1", "1"));
        // 1
        System.out.println(concurrentHashMap.putIfAbsent("1", "1"));
        
//        list.add(1);
//        list.add(1);
//        list.add(2);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        
        System.out.println("======================================================");
        Map<Integer, List<Integer>> map = list.stream()
                .filter(distinctByKey(p -> p))
                .collect(Collectors.toMap(p -> p, p -> {
                    System.out.println("forEach:aaa " + p);
                return new ArrayList<>();}));
        System.out.println("======================================================");
        list.stream()
                .filter(s -> {
                    System.out.println("测试调用几次:qqq " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach4: " + s));;
        System.out.println("======================================================");

        System.out.println(map);
    }

    /**
     * 根据某字段去重
     * @param keyExtractor
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        System.out.println("测试调用几次，实际只会调用一次");
        return object -> {
            System.out.println("测试Predicate中调用几次，会执行多次，只会在Predicate对象中循环调用test方法，进行判断过滤");
            return seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
        };
    }
    
}
