package xyz.frame.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration  
//@PropertySource("classpath:application-dev.properties")  
@ImportResource({ "classpath:dubbo.xml" })  
public class DubboConfig {  
  
}  