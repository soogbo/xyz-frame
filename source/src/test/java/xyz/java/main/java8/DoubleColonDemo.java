package xyz.java.main.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * java8方法引用(双冒号)：就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下
 * 
 * @author shisp
 * @date 2018-12-14 13:51:02
 */
public class DoubleColonDemo {
    public static void printValur(String str) {
        System.out.println("print value : " + str);
    }

    public static void main(String[] args) {
        // 方式1：一般写法
        List<String> al = Arrays.asList("a", "b", "c", "d");
        for (String a : al) {
            DoubleColonDemo.printValur(a);
        }
        // 下面的for each循环和上面的循环是等价的
        al.forEach(x -> {
            DoubleColonDemo.printValur(x);
        });
        
        // 方式2：双冒号使用
        List<String> al2 = Arrays.asList("a", "b", "c", "d");
        al2.forEach(DoubleColonDemo::printValur);
        //下面的方法和上面等价的
        Consumer<String> methodParam = DoubleColonDemo::printValur; //方法参数
        al.forEach(x -> methodParam.accept(x));//方法执行accept
    }
}
