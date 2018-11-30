package xyz.java.main.speedlimiter;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;

/**
 * @author shisp
 * @date 2018-9-20 09:23:45
 * @param <T>
 */
public class SpeedLimiter<T> {
    ConcurrentLinkedDeque<Long> statisticsInfo = new ConcurrentLinkedDeque<Long>();
    T resource = null;
    
    //下面两个参数用于限制访问速度
    
    //最大访问次数
    private int maxUsedLimited;
    //统计的时间长度为1000毫秒,即1秒
    private int timeLimited;
    
    
    //两个参数maxUsedLimited和timeLimited用于限制访问速度，表示在timeLimited毫秒内，只允许资源resource被使用maxUsedLimited次
    public SpeedLimiter(int maxUsedLimited, int timeLimited, T resource)
    {
        this.maxUsedLimited = maxUsedLimited;
        this.timeLimited = timeLimited;
        this.resource = resource;
        m_semaphore = new Semaphore(maxUsedLimited * 1000 / timeLimited);
    }

    //用信号量来限制资源的访问
    private Semaphore m_semaphore = null;

    public synchronized T consume()
    {
        if(statisticsInfo.size() >= maxUsedLimited)
        {//已经有了使用时间记录
            long ti = statisticsInfo.getLast() - statisticsInfo.getFirst();
            if(ti < timeLimited)
            {//已经超限制了，即次数达到了，但是时间还没到STATISTICS_TIME，说明申请的太快了，还差多少时间就sleep多少时间
                System.out.println("-------------资源访问受限，当前已经在"+ ti +"毫秒内访问了" + statisticsInfo.size() + "次资源");
                safeSleep(timeLimited - ti);
            }
            statisticsInfo.removeFirst();//总次数到了，每次都把最老的那个时间去掉
        }
        //先申请再记录时间
        try {
            m_semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        statisticsInfo.add(System.currentTimeMillis());
        return resource;
    }

    public void returnResource()
    {
        System.out.println("-------------回收一个资源，当前资源可用数：" + m_semaphore.drainPermits());
        m_semaphore.release();
    }

    private void safeSleep(long sleepTime)
    {
        try
        {
            Thread.sleep(sleepTime);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}