package xyz.frame.configure.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis config, 使用jedis 连接
 * 
 * @author shisp
 * @date 2018-7-30 09:30:35
 */
@Configuration
@EnableAutoConfiguration
public class RedisJedisConfig {
    private static Logger logger = Logger.getLogger(RedisJedisConfig.class);

    // 获取springboot配置文件的值 (get的时候获取)
    @Value(value = "${spring.redis.hostName}")
    private String redisHost;

    @Value(value = "${spring.redis.port}")
    private int redisPort;

    @Value(value = "${spring.redis.password}")
    private String redisPwd;

    @Value(value = "${spring.redis.timeout}")
    private int redisTimeout;

    @Value(value = "${spring.redis.database}")
    private int redisDatabase;

    /**
     * @Bean 和 @ConfigurationProperties
     * @return
     */
    @Bean("frameJedisPoolConfig")
    @ConfigurationProperties(prefix = "spring.redis.pool")
    public JedisPoolConfig getRedisConfig() {
        return new JedisPoolConfig();
    }

    @Bean("frameJedisPool")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public JedisPool getJedisPool(@Qualifier("frameJedisPoolConfig") JedisPoolConfig config) {
        JedisPool jedisPool = new JedisPool(config, redisHost, redisPort, redisTimeout, redisPwd, redisDatabase);
        logger.info("jedisPool bean init success.");
        return jedisPool;
    }

}
