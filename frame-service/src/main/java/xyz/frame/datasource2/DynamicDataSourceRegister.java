package xyz.frame.datasource2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.alibaba.druid.pool.DruidDataSource;

import xyz.frame.datasource.DataSourceHolder;
import xyz.frame.datasource.DataSourceProperties;
import xyz.frame.datasource.DataSourceProperties.Cluster;
import xyz.frame.datasource.DataSourceProperties.JdbcConfig;
import xyz.frame.utils.ConfigurePropertiesIoStream;

/**
 * 动态数据源注册
 * 1.application启动类上面要加上@Import({ DynamicDataSourceRegister.class })，在启动时就会加载
 * 2.此类在Spring刚启动时加载注册bean，无法使用Spring容器中的bean(此时还未注册进去)
 * 
 * @author shisp
 * @date 2018-12-03 13:48:37
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    public DataSourceProperties dataSourceProperties() {
        DataSourceProperties properties = new DataSourceProperties();
        Cluster cluster = new Cluster();
        properties.setCluster(cluster);

        JdbcConfig jdbcConfig1 = new JdbcConfig();
        jdbcConfig1.setUrl(ConfigurePropertiesIoStream.getProperty("xyz.db.cluster.master2.url"));
        jdbcConfig1.setUsername(ConfigurePropertiesIoStream.getProperty("xyz.db.cluster.master2.username"));
        jdbcConfig1.setPassword(ConfigurePropertiesIoStream.getProperty("xyz.db.cluster.master2.password"));

        JdbcConfig jdbcConfig2 = new JdbcConfig();
        jdbcConfig2.setUrl(ConfigurePropertiesIoStream.getProperty("xyz.db.cluster.slave2.url"));
        jdbcConfig2.setUsername(ConfigurePropertiesIoStream.getProperty("xyz.db.cluster.slave2.username"));
        jdbcConfig2.setPassword(ConfigurePropertiesIoStream.getProperty("xyz.db.cluster.slave2.password"));

        cluster.setMaster(jdbcConfig1);
        cluster.setSlaves(Arrays.asList(jdbcConfig2));

        return properties;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        DataSourceProperties properties = dataSourceProperties();
        // 主数据源
        DruidDataSource masterDs = buildDruidDataSource(properties, properties.getCluster().getMaster());
        // 从数据源
        List<JdbcConfig> slaveConfigList = properties.getCluster().getSlaves();
        List<DataSource> slaveDsList = slaveConfigList.stream().map(slaveConfig -> buildDruidDataSource(properties, slaveConfig)).collect(Collectors.toList());

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceHolder.DataSourceType.MASTER.name(), masterDs);
        targetDataSources.put(DataSourceHolder.DataSourceType.SLAVE.name(), slaveDsList.get(0));

        DynamicDataSourceContextHolder.addDataSourceIds(DataSourceHolder.DataSourceType.MASTER.name());
        DynamicDataSourceContextHolder.addDataSourceIds(DataSourceHolder.DataSourceType.SLAVE.name());

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        beanDefinition.setPrimary(true);
        beanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", masterDs);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("frameDataSource", beanDefinition);
        logger.debug("Dynamic DataSource Registry");
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