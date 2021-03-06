/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package xyz.frame.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @Description mvc配置 1.@Configuration 2.extends WebMvcConfigurerAdapter or
 *              WebMvcConfigurerSupport
 * @author liuzh
 * @since 2015-12-19 16:16
 */
//@EnableWebMvc //启用mvc java config
    //测试，开启此注解，Junit启动报错，不开启可正常使用
    //class与yml均可进行配置，Class会覆盖xml

// @Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    //静态文件访问：
	    //1.默认webapp下文件可以直接访问：127.0.0.1:8080/1.txt
	    //2.默认classpath:static文件夹下(即src/main/resources/static/下)可直接访问：127.0.0.1:8080/1.txt
	    // 1.2.可同时访问，相同路径同名文件，会访问到webapp下。
	    super.addResourceHandlers(registry); //使用jsp时建议使用此方法在webapp下维护静态文件
	    
	    //3.重写此方法，可设置文件存放路径，与访问需添加的路径，此时webapp中文件不能访问。
	    //  addResourceHandler：/aaa/(访问路径是：127.0.0.1:8080/aaa/1.txt或127.0.0.1:8080/1.txt)，一级目录下/aaa/1.txt或/1.txt都可，二级目录下必须/aaa/bbb/1.txt
	    //  addResourceLocations：/bbb/(文件放在/bbb/下)
		//  registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    } 
}
