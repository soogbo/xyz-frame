package xyz.frame.configure.schedule.quartz;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@ComponentScan("xyz.frame")
public class BatchConfigure {	
	
	@Bean(name = "schedulerFactoryBean")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)	
	public SchedulerFactoryBean getSchedulerFactoryBean(BatchJobFactory batchJobFactory){
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setJobFactory(batchJobFactory);
		return schedulerFactoryBean;
	}
	
}
