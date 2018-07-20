package xyz.frame.configure;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.alibaba.druid.pool.DruidDataSource;

import xyz.frame.configure.DataSourceProperties.JdbcConfig;


@Configuration
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean("dataSourceProperties")
    @ConfigurationProperties("xyz.db")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 动态数据源
     *
     * @return 动态数据源
     */
    @Bean(name = "frameDataSource")
    @Primary
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DataSource dataSource(@Qualifier("dataSourceProperties") DataSourceProperties dataSourceProperties) {
        logger.info("jdbc:{}", dataSourceProperties);
        return buildDruidDataSource(dataSourceProperties, dataSourceProperties.getCluster().getMaster());
    }

    private DruidDataSource buildDruidDataSource(DataSourceProperties dataSourceProperties, JdbcConfig jdbcConfig) {

        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(jdbcConfig.getUrl());
        ds.setUsername(jdbcConfig.getUsername());
        ds.setPassword(jdbcConfig.getPassword());

        ds.setDriverClassName(dataSourceProperties.getDriverClassName());
        ds.setInitialSize(dataSourceProperties.getInitialSize());
        ds.setMinIdle(dataSourceProperties.getMinIdle());
        ds.setMaxActive(dataSourceProperties.getMaxActive());
        ds.setMaxWait(dataSourceProperties.getMaxWait());
        ds.setTimeBetweenEvictionRunsMillis(dataSourceProperties.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(dataSourceProperties.getMinEvictableIdleTimeMillis());
        ds.setValidationQuery(dataSourceProperties.getValidationQuery());
        ds.setTestWhileIdle(dataSourceProperties.isTestWhileIdle());
        ds.setTestOnBorrow(dataSourceProperties.isTestOnBorrow());
        ds.setTestOnReturn(dataSourceProperties.isTestOnReturn());
        return ds;
    }

}
