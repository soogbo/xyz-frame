package xyz.frame.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SpringBeanUtil
 * @Description: TODO(spring功能类，用于获取bean)
 * 
 */
@Component("springBeanUtil")
public class SpringBeanUtil implements ApplicationContextAware {
    protected final static Log logger = LogFactory.getLog(SpringBeanUtil.class);

    private static ApplicationContext ctx = null;

    private static Map<String, Properties> propMap = new HashMap<String, Properties>(0);

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        SpringBeanUtil.ctx = ctx;
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    public static <T> Collection<T> getListServiceByType(Class<T> type) {
        Map<String, T> beansOfType = ctx.getBeansOfType(type);
        if (null!=beansOfType && beansOfType.size()>0) {
            return beansOfType.values();
        }
        return null;
    }

    /*public static <T> T getBean(String prop) {
        Object obj = ctx.getBean(prop);
        if (logger.isDebugEnabled()) {
            logger.debug("property=[" + prop + "],object=[" + obj + "]");
        }
        return (T) obj;
    }*/

    public static Properties getProperties(String filepath) {
        if (propMap.containsKey(filepath))
            return propMap.get(filepath);

        Resource resource = ctx.getResource(filepath);
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