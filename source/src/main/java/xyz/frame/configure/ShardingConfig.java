package xyz.frame.configure;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
import xyz.frame.mybatisplugin.annotation.ShardingMapper;
import xyz.frame.sharding.algorithm.UserTableRule;

@Configuration
public class ShardingConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShardingConfig.class);
    
    @Bean(name = "shardingDataSource")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DataSource getShardingDataSource(@Qualifier("frameDataSource") DataSource dataSource) throws SQLException {
        final String logicDataSource = "ds";
        // 配置真实数据源与逻辑数据源对应关系
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put(logicDataSource, dataSource);
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        //库名和表名支持groovy语法
        
        shardingRuleConfig.getTableRuleConfigs().add(new UserTableRule(logicDataSource, "0..2"));
        shardingRuleConfig.setDefaultDataSourceName(logicDataSource);
        // 获取数据源对象
        DataSource shardingDataSource = null;
        try {
            shardingDataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap<>(), new Properties());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        return shardingDataSource;
    }

    @Bean(name = "shardingSqlSessionFactory")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SqlSessionFactoryBean getSqlSessionFactoryBean(
            @Qualifier("shardingDataSource") DataSource dataSource,
            MybatisProperties mybatisProperties) {
        
        // 使用mybatis配置文件中自动注入后，使用MybatisProperties，
        // 需要开启(启动类)@EnableConfigurationProperties，或者使用类上面开启@EnableConfigurationProperties({MybatisProperties.class})
        SqlSessionFactoryBean sql = new SqlSessionFactoryBean();
        sql.setDataSource(dataSource);
        sql.setConfiguration(mybatisProperties.getConfiguration());
        return sql;
    }

    @Bean(name = "shardingMapperScannerConfigurer")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        String basePackage = "xyz.frame.**.mapper";
        msc.setBasePackage(basePackage);
        msc.setSqlSessionFactoryBeanName("shardingSqlSessionFactory");
        msc.setAnnotationClass(ShardingMapper.class);
        return msc;
    }

}
