package xyz.java.main.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 函数式接口Predicate使用
 * 
 * @author shisp
 * @date 2018-12-14 15:00:38
 */
public class PredicateTest {

    public static void main(String[] args) {
        predicates();
        predicates2();
    }

    private static void predicates2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        PredicateTest predicateTest = new PredicateTest();
        // 输出大于5的数字
        List<Integer> result = predicateTest.conditionFilter(list, integer -> integer > 5);
        result.forEach(System.out::println);
        System.out.println("-------");
        // 输出大于等于5的数字
        result = predicateTest.conditionFilter(list, integer -> integer >= 5);
        result.forEach(System.out::println);
        System.out.println("-------");
        // 输出小于8的数字
        result = predicateTest.conditionFilter(list, integer -> integer < 8);
        result.forEach(System.out::println);
        System.out.println("-------");
        // 输出所有数字
        result = predicateTest.conditionFilter(list, integer -> true);
        result.forEach(System.out::println);
        System.out.println("-------");
    }

    // 高度抽象的方法定义，复用性高
    public <T> List<T> conditionFilter(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * Predicates,只有一个输入参数布尔类型的函数 Java 8中，Predicate是一个函数式接口，可以被应用于lambda表达式和方法引用
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
}