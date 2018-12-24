package xyz.java.main.thread;

import java.util.concurrent.TimeUnit;

/**
 * join：当前线程A等待thread线程终止之后才从thread.join()返回
 * 
 * @author shisp
 * @date 2018-12-24 16:23:33
 */
public class Join {
    
    public static void main(String[] args) throws Exception {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            // 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable {
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
    
    // jdk,thread.join()的部分源码，模拟展示
    // 加锁当前线程对象
    public final synchronized void join() throws InterruptedException {
        // 条件不满足，继续等待
        while (isAlive()) {
            wait(0);
        }
        // 条件符合，方法返回
    }

    private boolean isAlive() {
        return false;
    }
    
}