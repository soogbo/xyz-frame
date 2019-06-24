package xyz.frame.configure;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * spring功能类，用于获取bean
 * 
 * @author shisp
 * @date 2018-4-10 10:05:32
 */
@Component("springContextHolder")
public class SpringContextHolder implements ApplicationContextAware {
    protected static final Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);
    /**
     * 上下文对象
     */
    private static ApplicationContext context = null;

    private static Map<String, Properties> propMap = new HashMap<>(0);

    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     * 
     * @return
     */
    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        SpringContextHolder.context = ctx;
    }

    /**
     * 获取上下文对象
     * 
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static <T> Collection<T> getListServiceByType(Class<T> type) {
        Map<String, T> beansOfType = context.getBeansOfType(type);
        if (null != beansOfType && beansOfType.size() > 0) {
            return beansOfType.values();
        }
        return null;
    }

    /**
     * 通过name获取 Bean.
     * 
     * @param name
     *            Bean名称
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     * 
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,class返回指定的Bean
     * 
     * @param name
     * @param clazz
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        T obj = getApplicationContext().getBean(name, clazz);
        if (logger.isDebugEnabled()) {
            logger.debug("name={},clazz={},getBean={}", name, clazz, obj);
        }
        return obj;
    }

    public static Properties getProperties(String filepath) {
        if (propMap.containsKey(filepath))
            return propMap.get(filepath);

        Resource resource = context.getResource(filepath);
        Properties prop = new Properties();
        try {
            prop.load(resource.getInputStream());
            propMap.put(filepath, prop);
            return prop;
        } catch (IOException e) {
            logger.error("can not find the resource file:[" + filepath + "]", e);
            return null;
        }
    }

}