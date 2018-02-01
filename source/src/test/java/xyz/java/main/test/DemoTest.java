package xyz.java.main.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class DemoTest {

    public static void main(String[] args) {

        test2();
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

    static void test3() {
        String a = "{\"url\":\"http://u6.gg/cnExz\",\"error\":0}";
        Object parse = JSON.parse(a);
        System.out.println(parse);
        test0();

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
