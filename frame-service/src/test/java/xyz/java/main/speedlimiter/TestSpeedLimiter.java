package xyz.java.main.speedlimiter;

/**
 * 
 * @author shisp
 * @date 2018-9-20 10:00:28
 */
public class TestSpeedLimiter {

    public static void main(String[] args) {
        String resource = "myresource";
        SpeedLimiter<String> test = new SpeedLimiter<String>(50, 1000, resource);
        TestSpeedLimitedThread<String> testThread1 = new TestSpeedLimitedThread<String>(test);
        testThread1.start();
        TestSpeedLimitedThread<String> testThread2 = new TestSpeedLimitedThread<String>(test);
        testThread2.start();
        TestSpeedLimitedThread<String> testThread3 = new TestSpeedLimitedThread<String>(test);
        testThread3.start();

        try {
            testThread1.join();
            testThread2.join();
            testThread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
