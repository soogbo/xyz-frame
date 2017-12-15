package xyz.frame.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xyz.frame.json.FrameJsonUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义http切面，对HTTP请求切入，进行日志记录
 */
@Aspect
@Component
public class HttpAspect {

	@Autowired
	private ExceptionHandle exceptionHandle;

	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

	// 切点，所有http请求的controller
	@Pointcut("execution(public * xyz.frame.controller..*.*(..))")
	public void log() {
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// url
		logger.info("url={}", request.getRequestURL());

		// method
		logger.info("method={}", request.getMethod());

		// ip
		logger.info("ip={}", request.getRemoteAddr());

		// 类方法
		logger.info("class_method={}",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

		// 参数
		logger.info("args={}", joinPoint.getArgs());
		
		//使用线程变量即时
		Profiler.begin();
	}

	@SuppressWarnings("unused")
	@Around("log()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		ResponseResult<?> result = null;
		try {

		} catch (Exception e) {
			return exceptionHandle.handle(e);
		}
		if (result == null) {
			return proceedingJoinPoint.proceed();
		}

		return result;
	}

	@After("log()")
	public void doAfter() {
		logger.info("aop doAfter");
	}

	@AfterReturning(returning = "object", pointcut = "log()")
	public void doAfterReturning(Object object) {
		logger.info("response={}", FrameJsonUtils.toJson(object));
		//使用线程变量即时
		logger.info("time cost={}", Profiler.end()/1000+" 秒");
	}

}
