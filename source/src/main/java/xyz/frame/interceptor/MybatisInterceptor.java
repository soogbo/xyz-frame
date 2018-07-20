package xyz.frame.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

/**
 * http://www.cnblogs.com/fangjian0423/p/mybatis-interceptor.html
 * 
 * mybatis 拦截器
 * @author shisp
 * @date 2018-4-28 15:51:18
 */
//@Intercepts({@Signature(
//        type= Executor.class,
//        method = "update",
//        args = {MappedStatement.class,Object.class})})
public class MybatisInterceptor implements Interceptor{

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MybatisInterceptor intercept");
        return null;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MybatisInterceptor plugin");

        return null;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("MybatisInterceptor setProperties");

    }

}
