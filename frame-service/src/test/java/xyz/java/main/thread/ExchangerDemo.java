package xyz.java.main.thread;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程间交换数据的Exchanger
 * 
 * @author shisp
 * @date 2019-1-28 15:48:42
 */
public class ExchangerDemo {
    private static final Exchanger<Long> exgr = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2000);
    static Long k = 0L;
    static Random random = new Random();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                // 随机休眠模拟业务代码
                TimeUnit.SECONDS.sleep(random.nextInt(2));
                if (k == 100 || k == 356 || k == 876) {
                    Long kk = exgr.exchange(k);
                    System.out.println("read exgr ==============" + kk);
                }
                System.out.println("R： K = " + k);
            } catch (Exception e) {
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                // 随机休眠模拟业务代码
                TimeUnit.SECONDS.sleep(random.nextInt(1));
                k++;
                System.out.println("W： K = " + k);
                if (k == 100 || k == 356 || k == 876) {
                    System.out.println("write write K ===============" + k);
                    exgr.exchange(k);
                }
            } catch (Exception e) {
            }
        });

        for (int i = 0; i < 1000; i++) {
            threadPool.execute(thread);
            threadPool.execute(thread2);
        }

        threadPool.shutdown();
    }

    // 写99 100 101,读98 100 101， 99没读到。100时被通知后读取
    // R： K = 98
    // W： K = 99
    // W： K = 100
    // write write K ===============100
    // W： K = 101
    // read exgr ==============100
    // R： K = 101
    // W： K = 102

}