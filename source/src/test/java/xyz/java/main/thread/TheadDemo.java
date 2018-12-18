package xyz.java.main.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程创建
 * 
 * @author shisp
 * @date 2018-12-18 11:00:15
 */
public class TheadDemo {

    public static void main(String[] args) {
        
        create1();
        create2();
        create3();
        
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 继承Thread
     */
    private static void create1() {
        Thread t1 = new MyThread();
        t1.setName("MyThread");
        // start方法异步执行，t1线程调用
        t1.start();
        System.out.println("TheadDemo1:" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TheadDemo2:" + Thread.currentThread().getName());
        // run方法同步执行，main线程调用
        t1.run();
    }
    
    /**
     * 实现Runnable
     */
    private static void create2() {
        Runnable t2 = new MyThreadRunnable();
        t2.run();
        Thread t1 = new Thread(t2);
        t1.setName("t1");
        t1.run();
        t1.start();
        
        //lambda
        Runnable t3 = ()-> System.out.println("t3:" + Thread.currentThread().getName());
        new Thread(t3).start();
        
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 线程池
     */
    private static void create3() {
        Runnable t4 = new MyThreadRunnable();
        
        // 单线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(t4);
        
        
    }
}
    
/**
 * 自定义线程：继承Thread
 */
class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("MyThread:" + Thread.currentThread().getName());
    }
}
/**
 * 自定义线程：实现Runnable
 */
class MyThreadRunnable implements Runnable {
    
    @Override
    public void run() {
        System.out.println("MyThreadRunnable:" + Thread.currentThread().getName());
    }
}
