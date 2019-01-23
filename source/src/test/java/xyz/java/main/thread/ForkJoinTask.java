package xyz.java.main.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin框架： 计算demo1+2+3+4
 * 
 * @author shisp
 * @date 2019-1-22 14:39:37
 */
public class ForkJoinTask extends RecursiveTask<Integer>{
    private static final long serialVersionUID = -682500814798866737L;

    private static final int THREAD_HOLD = 3;

    private int start;
    private int end;

    public ForkJoinTask(int start,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小就计算
        boolean canCompute = (end - start) <= THREAD_HOLD;
        if(canCompute){
            for(int i=start;i<=end;i++){
                sum += i;
            }
        }else{
            int middle = (start + end) / 2;
            ForkJoinTask left = new ForkJoinTask(start,middle);
            ForkJoinTask right = new ForkJoinTask(middle+1,end);
            //执行子任务
            left.fork();
            right.fork();
            //获取子任务结果
            int lResult = left.join();
            int rResult = right.join();
            sum = lResult + rResult;
        }
        return sum;
    }

    public static void main(String[] args){
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask task = new ForkJoinTask(1,10);
        Future<Integer> result = pool.submit(task);
        
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}