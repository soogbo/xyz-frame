package xyz.frame.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xyz.frame.json.FrameJsonUtils;
import xyz.frame.utils.ExceptionHandle;
import xyz.frame.utils.Profiler;

/**
 * 定义http切面，对HTTP请求切入，进行日志记录
 */
@Aspect
@Component
public class HttpAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    private ExceptionHandle exceptionHandle;

    @Pointcut("execution(public * xyz.frame..*Controller.*(..)) && !within(xyz.frame..WebSocketController)")
    public void httplog() {
        // 切点，所有http请求的controller
        //    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)||@annotation(org.springframework.web.bind.annotation.GetMapping)||@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    }

    @Before("httplog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // url
        logger.info("HTTP Request：url={}", request.getRequestURL());
        // method
        logger.info("method={}", request.getMethod());
        // ip
        logger.info("ip={}", request.getRemoteAddr());
        // 类方法
        logger.info("class_method={}",
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // 参数
        logger.info("args={}", joinPoint.getArgs());
        // 使用线程变量即时
        Profiler.begin();
    }

    @Around("httplog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            return proceedingJoinPoint.proceed();
        } catch (Exception e) {
            return exceptionHandle.handle(e);
        }
    }

    @After("httplog()")
    public void doAfter() {
        logger.info("aop doAfter");
    }

    @AfterReturning(returning = "object", pointcut = "httplog()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", FrameJsonUtils.toJson(object));
        // 使用线程变量即时
        logger.info("time cost={}", Profiler.end() / 1000 + " 秒");
        logger.info("====== HTTP Request END ======");
    }

}
