package xyz.java.main.java8;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * ConcurrentMap
 * 
 * @author shisp
 * @date 2018-12-27 22:30:27
 */
public class ConcurrentMapDemo {

    public static void main(String[] args) {
        
        /*
         * ConcurrentMap.put（a,b）
         *  返回null：表示插入成功
         *  返回插入值 b：表示检查map中有a-b键值对，返回值b
         *  返回值c：表示检查map中有a-c键值对，不更新a的值，并返回已有的值c
         * 
         *  总结：没有key对应值时，插入成功返回null，有key对应值时不更新并返回已有的值
         */
        
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        String put = map.put("1", "11");
        String put2 = map.put("1", "11");
        String put3 = map.put("1", "22");
        System.out.println(put);
        System.out.println(put2);
        System.out.println(put3);
        String putIfAbsent = map.putIfAbsent("2", "22");
        System.out.println(putIfAbsent);
        String putIfAbsent2 = map.putIfAbsent("2", "22");
        System.out.println(putIfAbsent2);
        String putIfAbsent3 = map.putIfAbsent("2", "33");
        System.out.println(putIfAbsent3);
        System.out.println(map);
        
        
    }
    
}
