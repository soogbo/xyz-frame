package xyz.frame.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 数据源设置
 */
//@Aspect
//@Component("frameDynamicDataSourceAspect")
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(xyz.frame.datasource.ReadSlave)")
    public void serviceExecution() {
        // 配置切面
    }

    @Around("serviceExecution()")
    @Order(100)
    public Object setDynamicDataSource(ProceedingJoinPoint jp) throws Throwable {
        // 获取读集群的数据源
        DataSourceHolder.setSlave();
        try {
            return jp.proceed();
        } finally {
            DataSourceHolder.clearDataSource();
        }
    }
}