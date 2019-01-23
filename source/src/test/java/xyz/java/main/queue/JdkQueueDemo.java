package xyz.java.main.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * java7种队列
 * 
 * @author shisp
 * @date 2019-1-22 09:26:52
 */
public class JdkQueueDemo {
    
    public static void main(String[] args) {
        // 一个由数组结构组成的有界阻塞队列。FIFO(先进先出)排序，默认非公平出队
        ArrayBlockingQueue();
        // 一个由链表结构组成的有界阻塞队列
        LinkedBlockingQueue();
        // 一个支持优先级排序的无界阻塞队列
        PriorityBlockingQueue();
        // 一个使用优先级队列实现的无界阻塞队列。支持延时获取元素的无界阻塞队列。
        DelayQueue();
        // 一个不存储元素的阻塞队列,适合传递性场景,吞吐量高于ArrayBlockingQueue LinkedBlockingQueue
        SynchronousQueue();
        // 一个由链表结构组成的无界阻塞队列
        LinkedTransferQueue();
        // 一个由链表结构组成的双向阻塞队列
        LinkedBlockingDeque();
    }

    private static void ArrayBlockingQueue() {
        // 默认出队不公平
        ArrayBlockingQueue<Integer> queueUnfire = new ArrayBlockingQueue<>(1000);
        // 创建公平出队队列
        ArrayBlockingQueue<Integer> queuefire = new ArrayBlockingQueue<>(1000, true);
        

    }

    private static void LinkedBlockingQueue() {
        // TODO Auto-generated method stub
        
    }

    private static void PriorityBlockingQueue() {
        // TODO Auto-generated method stub
        
    }

    private static void DelayQueue() {
        // TODO Auto-generated method stub
        
    }

    private static void SynchronousQueue() {
        // TODO Auto-generated method stub
        
    }

    private static void LinkedTransferQueue() {
        // TODO Auto-generated method stub
        
    }

    private static void LinkedBlockingDeque() {
        // TODO Auto-generated method stub
        
    }

}
