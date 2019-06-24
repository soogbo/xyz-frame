package xyz.frame.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

//@org.springframework.context.annotation.Configuration
public class HbaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(HbaseConfig.class);

    @Value("${hbase.zookeeper.quorum}")
    private String quorum;
    
    @Bean("hbaseConfiguration")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Configuration getHbaseConfiguration() {
        logger.info("init hbaseConfiguration...");
        quorum = "172.17.16.221:2181";
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", quorum);
        return configuration;
    }
    
    @Bean("hbaseTemplate")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public HbaseTemplate getHbaseTemplate() {
        logger.info("init hbaseTemplate...");
        Configuration hbaseConfiguration = getHbaseConfiguration();
        return new HbaseTemplate(hbaseConfiguration);
    }
    
    @Bean("hbaseConnection")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Connection getHbaseConnection() {
        logger.info("init hbaseTemplate success");
        Connection conn = null;
        try {
            conn = ConnectionFactory.createConnection(getHbaseConfiguration());
        } catch (IOException e) {
            logger.error("get hbase cobbection fail", e);
        }
        return conn;
    }

}
