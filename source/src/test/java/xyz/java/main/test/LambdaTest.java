package xyz.java.main.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * jdk1.8 表达式测试
 * @author shisp
 * @date 2018-8-23 14:05:28
 */
public class LambdaTest {
    
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(1);
        list2.add(2);
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>() {
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
