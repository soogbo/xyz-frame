package xyz.frame.utils;


import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.apache.shardingsphere.core.strategy.keygen.UUIDShardingKeyGenerator;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * 分布式主键
 *
 * @author soogbo
 * @date 2019
 */
@Component
public class ShardingKeyGenerator {

    private UUIDShardingKeyGenerator uUIDShardingKeyGenerator = new UUIDShardingKeyGenerator();
    private SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();


    public String uuidShardingKey() {
        return uUIDShardingKeyGenerator.generateKey().toString();
    }

    public String snowflakeShardingKey() {
        return snowflakeShardingKeyGenerator.generateKey().toString();
    }

    public static void main(String[] args) throws InterruptedException {
        ShardingKeyGenerator shardingKeyGenerator = new ShardingKeyGenerator();

        System.out.println("=======================================");
        for (int i = 0; i < 100; i++) {
            System.out.println(shardingKeyGenerator.uuidShardingKey());
        }
        System.out.println("=======================================");

        long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int p = 0; p < 100000; p++) {
                    System.out.println(shardingKeyGenerator.snowflakeShardingKey());
                }
                countDownLatch.countDown();
            }).run();

        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        // 1000万，25s
        System.out.println((end-start) + "ms =======================================");
    }

}
