package xyz.java.main.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断：这种终止线程的做法显得更加安全和优雅
 * suspend()、resume()和stop()方法带来副作用，是过期方法
 * 
 * @author shisp
 * @date 2018-12-24 15:06:01
 */
public class Shutdown {
    
    public static void main(String[] args) throws Exception {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "CountThread");
        countThread.start();
        // 睡眠1秒，main线程对CountThread进行中断，使CountThread能够感知中断而结束
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();
        // 睡眠1秒，main线程对Runner two进行取消，使CountThread能够感知on为false而结束
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }

    private static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("已中断：Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }
}