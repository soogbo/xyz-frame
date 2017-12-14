/**
 * 
 */
package xyz.frame.configure.webmvc;

import org.apache.ibatis.mapping.Environment;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description MybatisConfig
 * @author shisp
 * @date 2017年12月14日  下午4:48:42
 */
@ConfigurationProperties
public class MybatisConfig {


    protected Environment environment;  
    
    protected boolean safeRowBoundsEnabled = true;  
    protected boolean mapUnderscoreToCamelCase = true;  
    protected boolean lazyLoadingEnabled = false;
}
