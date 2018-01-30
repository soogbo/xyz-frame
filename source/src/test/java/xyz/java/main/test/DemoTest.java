package xyz.java.main.test;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class DemoTest {

    public static void main(String[] args) {
        test0();

    }

    static void test0() {
        String aa = "{'credit_bank_card':'6227445985467895123','credit_bank_name':'工商银行'}";
        @SuppressWarnings("unchecked")
        Map<String,String> parsea = JSON.parseObject(aa,Map.class);
        System.out.println(parsea);
        
        String aaa = "{\"url\":\"http://u6.gg/cnExz\",\"error\":0}";
        Object parse = JSON.parse(aaa);
        System.out.println(parse);

        JSONObject parseObject = JSONObject.parseObject(aa);
        System.out.println(parseObject);
    }

    static void test1() {

        String s = "10086,10086,吴元东,17621629003,吴元西,17621629004,,吴元南,17621629005";
        int inx = 0;
        String[] split = s.split(",", 32);
        System.out.println(Long.valueOf(split[inx++]));
        System.out.println(split[inx++]);
    }
}
