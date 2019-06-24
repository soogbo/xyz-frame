package xyz.java.main.thread;

import java.util.concurrent.TimeUnit;

/**
 * 同步屏障
 * 
 * @author shisp
 * @date 2019-1-28 15:30:58
 */
public class CyclicBarrierDemo {
    
//    static CyclicBarrier c = new CyclicBarrier(2);
    static int k = 0;
    
    public static void main(String[] args) {
        
        Thread thread = new Thread(() -> {
            try {
                /*if (k == 100) {
//                    c.await();
                    System.out.println("read K " + k);
                }*/
                System.out.println("read read K = " + k);
            } catch (Exception e) {
            }
        });
        
        Thread thread2 = new Thread(() -> {
            try {
                k++;
                System.out.println("write write K " + k);
                if (k == 100) {
//                    c.await();
                }
            } catch (Exception e) {
            }
        });
        
        
        for (int i = 0; i < 1000; i++) {
            thread.run();
            thread2.run();
        }
        
        try {
            TimeUnit.SECONDS.sleep(9999L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}