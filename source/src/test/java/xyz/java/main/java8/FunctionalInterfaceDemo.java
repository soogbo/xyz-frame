package xyz.java.main.java8;

/**
 * lambda在函数中的引用
 * 
 * @author shisp
 * @date 2018-12-14 10:59:17
 */
public class FunctionalInterfaceDemo {

    interface Converter<F, T> {
        T convert(F source);
    }

    public static void main(String[] args) {
        // 方式0：匿名内部类
        Converter<String, Integer> converter0 = new Converter<String, Integer>() {
            @Override
            public Integer convert(String source) {
                return Integer.valueOf(source);
            }
        };
        Integer convert0 = converter0.convert("0");
        System.out.println(convert0);
        
        // 方式1：函数式接口
        Converter<String, Integer> converter1 = (source) -> Integer.valueOf(source);
        Integer convert1 = converter1.convert("1");
        System.out.println(convert1);

        // 方式2：方法和构造函数引用
        Converter<String, Integer> converter2 = Integer::valueOf;
        Integer convert2 = converter2.convert("2");
        System.out.println(convert2);
    }

}
