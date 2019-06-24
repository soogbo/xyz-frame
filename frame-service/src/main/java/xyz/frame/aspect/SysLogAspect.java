package xyz.frame.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import xyz.frame.utils.Profiler;

/**
 * 对自定义注解切入，系统日志记录
 */
@Aspect
@Component
public class SysLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

    // 切点，所有注解@MongoLog的方法
    @Pointcut("@annotation(xyz.frame.utils.SysLog)")
    public void sysLog() {
    }

    @Before("sysLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 参数
        logger.info("自定义注解日志记录，doBefore：{}", joinPoint.getArgs());
        logger.info("自定义注解日志记录，className：{}", joinPoint.getClass().getName());
        // 类方法
        logger.info("自定义注解日志记录，classMethod={}",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // 使用线程变量即时
        Profiler.begin();
    }

//    @Around("sysLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        /*try {
            return proceedingJoinPoint.proceed();
        } catch (Exception e) {
            return exceptionHandle.handle(e);
        }*/
        return null;
    }

    @After("sysLog()")
    public void doAfter() {
        logger.info("aop doAfter");
        // 使用线程变量即时
        
        logger.info("sys日志记录处理操作！！！");
        logger.info("time cost={}", Profiler.end() / 1000 + " 秒");
    }

}
