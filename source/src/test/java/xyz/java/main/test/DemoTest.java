package xyz.java.main.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class DemoTest {

    public static void main(String[] args) {

        test4();
    }

    /**
     * list倒序，null放最后面
     */
    static void test4() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(null);
        list.add(3);
        list.add(2);
        System.out.println(list);
        list.sort((v1, v2) -> {
            if (v1 == null) {
                return 1;
            } else if (v2 == null || v1 > v2) {
                return -1;
            } else {
                return 0;
            }
        });
        list.subList(1, list.size()).clear();
        System.out.println(list);
    }
    
    static void test3() {
        String a = "{\"url\":\"http://u6.gg/cnExz\",\"error\":0}";
        Object parse = JSON.parse(a);
        System.out.println(parse);
        test0();

    }
    
    static void test2() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(1);
        arrayList.add(1);

        if (arrayList.get(3) == null) {
            System.out.println("jhahaha"); // java.lang.IndexOutOfBoundsException
        }
    }

    static void test0() {
        String aa = "{'credit_bank_card':'6227445985467895123','credit_bank_name':'工商银行'}";
        @SuppressWarnings("unchecked")
        Map<String, String> parsea = JSON.parseObject(aa, Map.class);
        System.out.println(parsea);
    }

    static void test1() {

        String s = "10086,10086,吴元东,17621629003,吴元西,17621629004,,吴元南,17621629005";
        int inx = 0;
        String[] split = s.split(",", 32);
        System.out.println(Long.valueOf(split[inx++]));
        System.out.println(split[inx++]);
    }
    
}
