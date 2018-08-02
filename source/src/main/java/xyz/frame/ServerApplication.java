package xyz.frame;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Description 启动类
 * @author shisp
 * @date 2017年11月27日 下午2:28:20
 */
@EnableCaching
@MapperScan(basePackages = "xyz.frame.**.mapper")
// 使用tk分页插件时，mapperScan把包名org修改为tk
@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration
public class ServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServerApplication.class);
	}

}
