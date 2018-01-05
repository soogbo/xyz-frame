package xyz.frame.main.test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class DemoTest {

    
    public static void main(String[] args) {
        String a = "{\"url\":\"http://u6.gg/cnExz\",\"error\":0}";
        Object parse = JSON.parse(a);
        System.out.println(parse);
        
        JSONObject parseObject = JSONObject.parseObject(a);
        System.out.println(parseObject);
        
        
    }
}
