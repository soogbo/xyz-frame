package xyz.java.main.test;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class DemoTest {

    
    public static void main(String[] args) {
        test2();
    }
    
    static void test2() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(1);
        arrayList.add(1);
        
        if (arrayList.get(3)==null) {
            System.out.println("jhahaha"); //java.lang.IndexOutOfBoundsException
        }
    }
    void test1() {
        String a = "{\"url\":\"http://u6.gg/cnExz\",\"error\":0}";
        Object parse = JSON.parse(a);
        System.out.println(parse);
        
        JSONObject parseObject = JSONObject.parseObject(a);
        System.out.println(parseObject);
    }
}
