package xyz.java.main.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

/**
 * 使用volatile定义多线程可见变量
 * 
 * 解决redis分布式锁造成死锁问题：
 * 1.redis锁设置较短失效时间，2.主线程获取锁执行任务，3.开一副线程定时刷新锁失效时间
 * 4.主线程结束后，volatile变量isCheck更新状态，副线程根据isCheck不再刷新锁，锁自动失效
 * 
 * @author shisp
 * @date 2018-5-11 14:04:44
 */
public class VolatileDemo {

    volatile private boolean isCheck = true;

    private Map<String, Long> localRedisMap = new ConcurrentHashMap<>(100);
    
    public static void main(String[] args) throws InterruptedException {

    }

    @Test
    public void test1() throws InterruptedException {
        // -------------------------
        // 模拟锁已存在，主线程等待，前一锁5秒失效后，主线程获取锁
        putLock("volatileDemo", 5000L);
        new Thread(() -> {
            try {
                Thread.sleep(5000L);
                releaseLock("volatileDemo");
                System.out.println("前一线程锁移除！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // -------------------------
        
        putLock("volatileDemo", 1000L);
        
        new Thread(() -> {
            while (isCheck) {
                refreshLock("volatileDemo", 1000L);
                System.out.println("副线程监控刷新锁："+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            releaseLock("volatileDemo");
            
            System.out.println("副线程check is over!!!!!!!!");
        }).start();
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程执行任务"+i);
            Thread.sleep(100L);
        }
        isCheck = false;
        System.out.println("主线程执行任务  over!!!!!!!!");

        Thread.sleep(10000000L);

        System.out.println("主线程wait check,保证主线程存在，副线程不会强制关闭!!!!!!!!");

    }
    
    /**
     * 设置锁
     * @param key
     * @param expiretime
     * @return
     * @throws InterruptedException 
     */
    private Long putLock(String key,Long expiretime) throws InterruptedException {
        Long puttime = null;
//        boolean containsKey = localRedisMap.containsKey(key);
        
        while(localRedisMap.containsKey(key)) {
            System.out.println("锁已存在，等待2s！！！");
            Thread.sleep(2000L);
        }
        puttime = localRedisMap.put(key, expiretime);
        System.out.println("设置锁："+key);
        
        return puttime;
    }
    /**
     * 释放锁
     * @param key
     * @return
     */
    protected Long releaseLock(String key) {
        Long removetime = null;
        boolean containsKey = localRedisMap.containsKey(key);
        if (containsKey) {
            removetime = localRedisMap.remove(key);
            System.out.println("释放锁："+key);
        }
        return removetime;
    }
    /**
     * 刷新锁
     * @param key
     * @param expiretime
     * @return
     */
    private Long refreshLock(String key,Long expiretime) {
        Long refreshtime = null;
        boolean containsKey = localRedisMap.containsKey(key);
        if (containsKey) {
            refreshtime = localRedisMap.put(key, expiretime);
            System.out.println("刷新锁："+key);
        }
        return refreshtime;
    }
}
