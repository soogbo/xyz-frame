package xyz.java.main.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * 邮戳读写锁:java8新增：StampedLock的锁方法会返回表示为long的标记，可以用标记来释放锁，或者检查锁是否有效 
 * 三种模式：
 * 1.写锁writeLock：排它锁，独占锁，不可重入 
 * 2.悲观读锁readLock：共享锁， （悲观认为在读时有写操作，所以使用时会获取到锁，此时不可写，在读少写多中的一种考虑）
 * 3.乐观读锁tryOptimisticRead，(乐观的认为在读时没有写操作，设置时没有cas设锁状态，在具体操作前需要调用validate验证下该stamp是否已经不可用，适用读多写少情况)
 * 
 * @author shisp
 * @date 2018-12-19 15:09:36
 */
public class StampedLockDemo {

    public static void main(String[] args) {
        stampedLock();
        
        tryOptimisticRead();
    }
    
    /**
     * 使用乐观读锁步骤
     */
    private static void tryOptimisticRead() {
        StampedLock lock = new StampedLock();
        long stamp = lock.tryOptimisticRead(); //非阻塞获取版本信息
        
        //copyVaraibale2ThreadMemory();//拷贝变量到线程本地堆栈
        if(!lock.validate(stamp)){ // 校验
            stamp = lock.readLock();//获取读锁
            try {
                //copyVaraibale2ThreadMemory();//拷贝变量到线程本地堆栈
             } finally {
               lock.unlock(stamp);//释放悲观锁
            }
            
        }
        //useThreadMemoryVarables();//使用线程本地堆栈里面的数据进行操作
    }

    /**
     * 邮戳读写锁:java8新增：
     * 三种模式：
     *  1.写锁writeLock：排它锁，独占锁，不可重入
     *  2.悲观读锁readLock：共享锁，
     *  3.乐观读锁tryOptimisticRead
     */
    private static void stampedLock() {
        StampedLock stampedLock = new StampedLock();
        // 获取写锁，获得long邮戳
        long stamp = stampedLock.writeLock();
        // 使用邮戳释放锁
        stampedLock.unlockWrite(stamp);
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            long stamp2 = stampedLock.writeLock();
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("stampedLock写锁持有，标记戳stamp2=" + stamp2);
            } catch (InterruptedException e) {
            } finally {
                stampedLock.unlockWrite(stamp2);
            }
        });
        
        Runnable readTask = () -> {
            long stamp3 = stampedLock.readLock();
            try {
                System.out.println("stampedLock读锁持有，标记戳stamp3=" + stamp3);
            } finally {
                stampedLock.unlockRead(stamp3);
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);
        
    }
    
    
    
}

class Point {
    // 成员变量
    private double x, y;
    // 锁实例
    private final StampedLock sl = new StampedLock();


    // 1.排它锁-写锁（writeLock）
    void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    // 2.乐观读锁（tryOptimisticRead）
    double distanceFromOrigin() {

        // 尝试获取乐观读锁（1）
        long stamp = sl.tryOptimisticRead();
        // 将全部变量拷贝到方法体栈内（2）
        double currentX = x, currentY = y;
        // 检查在（1）获取到读锁票据后，锁有没被其他写线程排它性抢占（3）
        if (!sl.validate(stamp)) {
            // 如果被抢占则获取一个共享读锁（悲观获取）（4）
            stamp = sl.readLock();
            try {
                // 将全部变量拷贝到方法体栈内（5）
                currentX = x;
                currentY = y;
            } finally {
                // 释放共享读锁（6）
                sl.unlockRead(stamp);
            }
        }
        // 返回计算结果（7）
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // 3.使用悲观锁获取读锁，并尝试转换为写锁
    void moveIfAtOrigin(double newX, double newY) {
        // 这里可以使用乐观读锁替换（1）
        long stamp = sl.readLock();
        try {
            // 如果当前点在原点则移动（2）
            while (x == 0.0 && y == 0.0) {
                // 尝试将获取的读锁升级为写锁（3）
                long ws = sl.tryConvertToWriteLock(stamp);
                // 升级成功，则更新票据，并设置坐标值，然后退出循环（4）
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    // 读锁升级写锁失败则释放读锁，显示获取独占写锁，然后循环重试（5）
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            // 释放锁（6）
            sl.unlock(stamp);
        }
    }
}

