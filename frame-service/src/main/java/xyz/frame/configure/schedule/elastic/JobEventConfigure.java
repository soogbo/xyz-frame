package xyz.frame.configure.schedule.elastic;

import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * elastic-job event configuration
 *
 * @author chenks
 */
//@Configuration
public class JobEventConfigure {

//    @Qualifier("frameworkDataSource")
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean
    public JobEventRdbConfiguration jobEventRdbConfiguration() {
        return new JobEventRdbConfiguration(dataSource);
    }
}
