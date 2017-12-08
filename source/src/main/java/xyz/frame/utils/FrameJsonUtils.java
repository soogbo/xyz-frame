/**
 * 
 */
package xyz.frame.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * @Description json工具类
 * @author shisp
 * @date 2017年12月8日  下午2:09:19
 */
public class FrameJsonUtils {

    // json String 转对象两种方式：1.String.class对象  2.泛型类对象
    // List<Integer> paramBo = JSON.parseObject(msg,new TypeReference<List<Integer>>(){});
    // String paramBo = JSON.parseObject(msg,String.class);
    
    /**
     * json集合字符串转集合
     * 封装 fastjson
     * @param text json string
     * @param type type refernce
     * @return
     */
    public static <T> T fromJson(String json, TypeReference<T> type){
        return JSON.parseObject(json,new TypeReference<T>(){});
    }
    
    /**
     * json对象字符串转对象
     * 封装 fastjson
     * @param json the string from which the object is to be deserialized
     * @param clazz the class of T
     * @return an object of type T from the string
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz){
        return JSON.parseObject(json,clazz);
    }
    
    /**
     * 对象转json
     * 封装 fastjson
     * @param src
     * @return json string
     */
    public static String toJson(Object src){
        return JSON.toJSONString(src);
    }
}
