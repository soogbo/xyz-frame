package xyz.frame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/**
 * @Description 启动类
 * @author shisp
 * @date 2017年11月27日  下午2:28:20
 */
@EnableWebMvc
@MapperScan(basePackages = "xyz.frame.mapper")
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}
