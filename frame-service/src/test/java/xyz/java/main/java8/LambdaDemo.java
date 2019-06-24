package xyz.java.main.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * jdk1.8 表达式测试
 * 
 * @author shisp
 * @date 2018-8-23 14:05:28
 */
public class LambdaDemo {

    public static void main(String[] args) {
        useFunction();
        sortForLambda();
    }
    
    /**
     * @date 2018-12-14 10:53:25
     */
    static void sortForLambda(){
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        }); 
        
        Collections.sort(names, (a, b)-> b.compareTo(a));
        names.sort((a, b)-> b.compareTo(a));
        
        Comparator<String> comparator = (a, b)-> b.compareTo(a);
        names.sort(comparator);
    }
    
    /**
     * @date 2018-8-23 14:05:28
     */
    static void useFunction() {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(1);
        list2.add(2);

        Map<Integer, Integer> map = new HashMap<Integer, Integer>() {
            private static final long serialVersionUID = 1L;
            {
                put(1, 11);
                put(3, 33);
            }
        };

        Function<Integer, BiConsumer<Integer, Integer>> fun = a -> (k, v) -> {
            if (a.equals(k)) {
                list.add(map.get(a));
            }
        };

        // map中的key如果存在在一个集合中，就吧map的value取出来放在另一个集合中
        list2.stream().map(fun).forEach(map::forEach);
        System.out.println(list);
    }
}
