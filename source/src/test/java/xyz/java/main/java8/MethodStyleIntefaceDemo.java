package xyz.java.main.java8;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 内置函数式接口 Predicates
 * 
 * @author shisp
 * @date 2018-12-14 14:22:23
 */
public class MethodStyleIntefaceDemo {

    public static void main(String[] args) {
        predicates();
        function();
        functionComposeAndThen();
        suppliers();
        consumer();
    }

    /**
     * Predicates,只有一个输入参数布尔类型的函数
     * Java 8中，Predicate是一个函数式接口，可以被应用于lambda表达式和方法引用
     */
    static void predicates() {
        Predicate<String> predicate = (s) -> s.length() > 0;
        // true
        System.out.println(predicate.test("foo"));
        // false
        System.out.println(predicate.negate().test("foo"));

        Predicate<Boolean> nonNull = Objects::nonNull;
        System.out.println(nonNull.test(null));
        
        Predicate<Boolean> isNull = Objects::isNull;
        System.out.println(isNull.test(null));

        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.test(""));
        
        Predicate<String> isNotEmpty = isEmpty.negate();
        System.out.println(isNotEmpty.test(""));
    }
    
    /**
     * function:接收一个参数，并返回单一的结果，默认方法可以将多个函数串在一起（compse, andThen）
     */
    static void function(){
        // 方式一
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("123"));
        
        // 方式二
        Function<String, Integer> toInteger2 = new Function<String, Integer>() {
            @Override
            public Integer apply(String t) {
                return Integer.valueOf(t);
            }
        };
        System.out.println(toInteger2.apply("123345"));
    }
    
    /**
     * function接口流式执行方法
     * Function:compose(Function before)，先执行参数before，再执行当前的function
     * Function:andThen(Function after)，先执行当前的function，再执行参数function after
     * 
     * BiFunction:传入两个入参一个出参；
     */
    static void functionComposeAndThen(){
        System.out.println(compute1(5,i -> i * 2,i -> i * i));//50
        System.out.println(compute2(5,i -> i * 2,i -> i * i));//100
    }

    public static int compute1(int i, Function<Integer,Integer> after,Function<Integer,Integer> before){
        return after.compose(before).apply(i);
    }
    public static int compute2(int i, Function<Integer,Integer> before,Function<Integer,Integer> after){
        return before.andThen(after).apply(i);
    }
    
    /**
     * Supplier接口产生一个给定类型的结果。与Function不同的是，Supplier没有输入参数
     * get获取泛型T的实例，可作为对象供应器
     */
    static void suppliers(){
        Supplier<MethodStyleIntefaceDemo> personSupplier = MethodStyleIntefaceDemo::new;
        MethodStyleIntefaceDemo methodStyleIntefaceDemo = personSupplier.get();
        System.out.println(methodStyleIntefaceDemo);
    }

    /**
     * Consumer代表了在一个输入参数上需要进行的操作
     */
    static void consumer(){
        Consumer<Integer> greeter = (p) -> System.out.println("Hello, " + p*100);
        Consumer<Integer> greeter2 = (p) -> System.out.println("Hello, " + p*999);
        Consumer<Integer> andThenGreeter = greeter.andThen(greeter2);
        andThenGreeter.accept(Integer.valueOf("100"));
        // 流式处理
        greeter.andThen(greeter2).accept(Integer.valueOf("100"));
    }
}
