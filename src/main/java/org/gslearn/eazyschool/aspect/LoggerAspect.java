package org.gslearn.eazyschool.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* org.gslearn.eazyschool..*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getSignature().getName()+ "Method Started");
        Instant startTime = Instant.now();
        Object result = joinPoint.proceed();
        Instant endTime = Instant.now();
        long elapsedTime = Duration.between(startTime, endTime).toMillis();
        log.info("Time took to execution" + joinPoint.getSignature().getName()+ "Method is"+elapsedTime);
        log.info(joinPoint.getSignature().getName()+ "Method Finished");
        return result;
    }

    @AfterThrowing(value = "execution(* org.gslearn.eazyschool..*.*(..))",throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint,Exception e) {
        log.error(joinPoint.getSignature().getName()+ "An exception occured"+e.getMessage());
    }
}
