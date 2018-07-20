package xyz.frame.mybatisplugin.annotation;

import java.lang.annotation.*;

/**
 * 不拦截sql注解，用在dao方法上，有此注解该方法不拦截Mybatis
 *
 * @CreateDate: 2018/3/28 14:24
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME) // 注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
@Target({ElementType.METHOD, ElementType.TYPE}) // 该注解修饰类中的方法
@Inherited
public @interface NoSqlInterceptor {
}
