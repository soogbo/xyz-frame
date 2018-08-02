package xyz.frame.configure.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * ShiroConfig
 * 
 * @author shisp
 * @date 2018-7-27 15:36:19
 */
@Configuration
@EnableAutoConfiguration
public class ShiroConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {

		LOGGER.info("ShiroConfiguration.shiroFilterFactoryBean() start..");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/login*", "anon");
		filterChainDefinitionMap.put("/api/forkf/*", "anon");
		// static目录下文件可直接访问
		filterChainDefinitionMap.put("/*", "anon");
		filterChainDefinitionMap.put("/resources/**", "anon");
		// webSocket连接可直接访问 
		filterChainDefinitionMap.put("/webSocketServer/**", "anon");
		// 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		// 过虑器
		Map<String, Filter> filters = new LinkedHashMap<>();
		filters.put("authc", new ShiroFormAuthenticationFilter());
		shiroFilterFactoryBean.setFilters(filters);
		return shiroFilterFactoryBean;
	}

	public DefaultSecurityManager securityManager() {

		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm(hashedCredentialsMatcher()));
		securityManager.setSessionManager(defaultWebSessionManager());
		// securityManager.setCacheManager(redisCacheManager);
		return securityManager;
	}

	@Bean
	public DefaultWebSessionManager defaultWebSessionManager() {

		DefaultWebSessionManager manager = new DefaultWebSessionManager();
		manager.setSessionValidationSchedulerEnabled(true);
		manager.setGlobalSessionTimeout(86400000);
		manager.setSessionDAO(shiroSessionDao());
		return manager;
	}
	
	@Bean
	public SessionRedisDao shiroSessionDao(){
		
		return new SessionRedisDao();
	}
	

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 自实现realm
	 * 
	 * @return
	 */
	@Bean(name = "frameShiroRealm")
	@DependsOn("lifecycleBeanPostProcessor")
	public FrameShiroRealm shiroRealm(CredentialsMatcher matcher) {
	    FrameShiroRealm shiroRealm = new FrameShiroRealm();
		shiroRealm.setCredentialsMatcher(matcher);
		return shiroRealm;
	}

	/**
	 * 密码匹配凭证管理器
	 *
	 * @return
	 */
    public CredentialsMatcher hashedCredentialsMatcher() {
        return (token, info) -> true;
    }
}
