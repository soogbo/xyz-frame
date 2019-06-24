package xyz.java.main.speedlimiter;

public class TestSpeedLimitedThread<T> extends Thread {
    SpeedLimiter<T> limiter = null;

    public TestSpeedLimitedThread(SpeedLimiter<T> limiter) {
        this.limiter = limiter;
    }

    public void run() {
        long i = 0;
        while (true) {
            T tmpResource = limiter.consume();
            System.out.println("thread id: " + currentThread().getId() + " : " + (i + 1) + ": 已经获取到资源：" + tmpResource);
            i++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            limiter.returnResource();
        }
    }
}