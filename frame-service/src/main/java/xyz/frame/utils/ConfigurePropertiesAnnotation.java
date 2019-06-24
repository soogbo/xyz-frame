package xyz.frame.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * 方式1：仅在sping容器管理下
 * 读取configure.properties配置文件：注解方式读取@Value()
 * encoding = "utf-8"属性防止中文乱码,不能为大写的"UTF-8"
 */
@Configuration  
@PropertySource(value = {"classpath:/configure.properties"},ignoreResourceNotFound = true,encoding = "utf-8")  
public class ConfigurePropertiesAnnotation {
	/**
	 * 日志
	 */
//	private static final Logger logger = LoggerFactory.getLogger(ConfigurePropertiesAnnotation.class);

	// PropertySourcesPlaceholderConfigurer这个bean，  
    // 这个bean主要用于解决@value中使用的${…}占位符。  
    // 假如你不使用${…}占位符的话，可以不使用这个bean。  
    @Bean  
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {  
        return new PropertySourcesPlaceholderConfigurer();  
    }

}
