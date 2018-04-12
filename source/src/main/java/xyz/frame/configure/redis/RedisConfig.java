package xyz.frame.configure.redis;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by liwenlong on 2018-04-02.
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig {
    private static Logger logger = Logger.getLogger(RedisConfig.class);

    //获取springboot配置文件的值 (get的时候获取)
	@Value(value = "${spring.redis.hostName}")
	private String redisHost;
	
	@Value(value = "${spring.redis.port}")
	private int redisPort;
	
	@Value(value = "${spring.redis.password}")
	private String redisPwd;
	
	@Value(value = "${spring.redis.timeout}")
	private int redisTimeout;
    
    /**
     * @Bean 和 @ConfigurationProperties
     * 该功能在官方文档是没有提到的，我们可以把@ConfigurationProperties和@Bean和在一起使用。
     * 举个例子，我们需要用@Bean配置一个Config对象，Config对象有a，b，c成员变量需要配置，
     * 那么我们只要在yml或properties中定义了a=1,b=2,c=3，
     * 然后通过@ConfigurationProperties就能把值注入进Config对象中
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.redis.pool")
    public JedisPoolConfig getRedisConfig() {
    	
        return new JedisPoolConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setUsePool(true);
        JedisPoolConfig config = getRedisConfig();
        factory.setPoolConfig(config);
        logger.info("JedisConnectionFactory bean init success.");
        return factory;
    }


    @Bean
    public RedisTemplate<?, ?> getRedisTemplate() {
        JedisConnectionFactory factory = getConnectionFactory();
        logger.info(this.redisHost+","+factory.getHostName()+","+factory.getDatabase());
        logger.info(this.redisPwd+","+factory.getPassword());
        logger.info(factory.getPoolConfig().getMaxIdle());
        RedisTemplate<?, ?> template = null;
        template = new StringRedisTemplate(factory);
        return template;
    }
    
    
    /**
     * redisManager
     * @return
    @Bean(name = "redisManager")
    public RedisManager redisManager() {
    	RedisManager redisManager  = new RedisManager();
    	redisManager.setHost(redisHost);
    	redisManager.setPort(redisPort);
    	redisManager.setPassword(redisPwd);
    	redisManager.setExpire(redisTimeout);
    	redisManager.setTimeout(redisTimeout);
    	return redisManager;
    }
     */

    /**
     * RedisSessionDAO
     * @return
    @Bean(name = "redisSessionDAO")
    public RedisSessionDAO redisSessionDAO() {
    	RedisSessionDAO sessionDAO = new RedisSessionDAO();
    	sessionDAO.setRedisManager(redisManager());
    	sessionDAO.setKeyPrefix("monitoceter_shiro_session:");
    	return sessionDAO;
    }
     */
    
    /**
     * RedisCacheManager
     * @return
    @Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager() {
    	RedisCacheManager cacheManager = new RedisCacheManager();
    	cacheManager.setRedisManager(redisManager());
    	return cacheManager;
    }
     */
    
    /**
     * sessionManager
     * @return
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
    	DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    	sessionManager.setSessionDAO(redisSessionDAO());
    	sessionManager.setCacheManager(redisCacheManager());
    	return sessionManager;
    }
     */
}
