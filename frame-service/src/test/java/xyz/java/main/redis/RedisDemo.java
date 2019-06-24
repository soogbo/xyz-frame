package xyz.java.main.redis;

public class RedisDemo {

    public static void main(String[] args) {
        setString();
        getString();
        delString();
        getString();
    }

    public static void setString() {
        RedisDb.setString("testRedisDb1", "testRedisDb1");
        System.out.println("redis set key:testRedisDb1");
    }

    public static void getString() {
        String string = RedisDb.getString("testRedisDb1");
        System.out.println("redis get key:" + string);
    }

    public static void delString() {
        Long delString = RedisDb.delString("testRedisDb1");
        System.out.println("redis del key:testRedisDb1");
        System.out.println(delString > 0 ? "del success" : "del fail");
    }

}
