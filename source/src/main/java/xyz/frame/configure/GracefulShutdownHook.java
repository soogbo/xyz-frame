package xyz.frame.configure;

import java.util.Collection;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

import redis.clients.jedis.JedisPool;
import xyz.frame.configure.rocketmq.RocketMQMessageConsumer;

/**
 * 停服时关闭资源钩子
 * 
 * @author shisp
 * @date 2018-7-30 11:24:44
 */
@Component
public class GracefulShutdownHook extends Thread {

    @Resource(name = "frameJedisPool")
    private JedisPool frameJedisPool;
    @Resource(name = "frameDataSource")
    private DataSource frameDataSource;
    @Resource(name = "commonTaskExecutor")
    private ThreadPoolTaskExecutor commonTaskExecutor;
    @Resource
    private JedisConnectionFactory jedisConnectionFactory;

    @Override
    public void run() {
        shutdownThreadPool();
        destoryRedisPool();
        shutdownMq();
        shutdownMongo();
        destoryMysqlPool();
    }

    /**
     * 关闭线程池
     */
    private void shutdownThreadPool() {
        // ThreadPoolConfigure中定义的线程池
        try {
            commonTaskExecutor.shutdown();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * 关闭redis连接池
     */
    private void destoryRedisPool() {
        // RedisJedisConfig
        try {
            frameJedisPool.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        
        // RedisTemplateConfig
        try {
            jedisConnectionFactory.destroy();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * 关闭mysql数据库连接池
     */
    private void destoryMysqlPool() {
        // DataSourceConfig中配置的数据源
        try {
            DruidDataSource master = (DruidDataSource) frameDataSource;
            master.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * 关闭mq
     */
    private void shutdownMq() {
        // 获取 mq消费实现列表
        try {
            Collection<RocketMQMessageConsumer> serviceList = SpringContextHolder.getListServiceByType(RocketMQMessageConsumer.class);
            if (null != serviceList && !serviceList.isEmpty()) {
                for (RocketMQMessageConsumer rmc : serviceList) {
                    rmc.shutdown();
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * 关闭mongo
     */
    private void shutdownMongo() {
        // mongoClient.close();
    }
}
