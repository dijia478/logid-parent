package cn.dijia478.logid.spring.boot.autoconfigure;

import cn.hutool.core.lang.ObjectId;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

/**
 * logId的切面
 *
 * @author dijia478
 * @date 2019-11-20 9:58
 */
@Aspect
@Configuration
public class LogIdAspect {

    /**
     * 定义切点Pointcut
     */
    @Pointcut("@annotation(cn.dijia478.logid.spring.boot.autoconfigure.LogId)")
    public void logIdExecution() {}

    /**
     * 在注解的方法加上logId，并在方法执行完后删除logId
     *
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around("logIdExecution()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            String logId = ObjectId.next();
            MDC.put("logId", logId);
            proceedingJoinPoint.proceed();
        } finally {
            MDC.clear();
        }
    }

}
