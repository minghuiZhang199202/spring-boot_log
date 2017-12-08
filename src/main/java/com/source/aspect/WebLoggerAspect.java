package com.source.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <p>@author minghuiZhang.</p>
 * <p>description：</p>
 * <p>date: created in 15:33 2017/12/8</p>
 * <p>modified By: </p>
 */
@Aspect
@Order(5)
@Component
public class WebLoggerAspect {
    private Logger logger = Logger.getLogger(WebLoggerAspect.class);
    private ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("execution(public * com.source.web..*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint)throws Throwable{
        startTime.set(System.currentTimeMillis());

        //接收到请求，记录请求内容

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //记录下请求内容
        logger.info("URL: " + request.getRequestURI());
        logger.info("HTTP_METHOD: " + request.getMethod());
        logger.info("IP: " + request.getRemoteAddr());
        logger.info("CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());

        logger.info("AGRS: " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret)throws Throwable{
        //处理完请求，返回内容
        logger.info("RESPONSE: "+ ret);
        logger.info("SPEND TIME: "+(System.currentTimeMillis() - startTime.get()));
    }
}
