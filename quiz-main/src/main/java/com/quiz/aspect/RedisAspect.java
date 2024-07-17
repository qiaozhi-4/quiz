package com.quiz.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>
 * RedisAspect
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/15 13:12
 */
@Aspect
@Log4j2
@Component
@RequiredArgsConstructor
public class RedisAspect {
    private final RedisTemplate<String, Object> redisTemplate;

    /* 定义切入方法后面可以复用 */
    @Pointcut("execution(*  com.quiz.service.impl.DeviceServiceImpl.change(..))")
    public void getAllPath() {
    }
    @Before("getAllPath()")
    public void before(JoinPoint joinPoint) {
        log.debug("方法运行之前");
    }

    @After("getAllPath()")
    public void after(JoinPoint joinPoint) {
        log.debug("方法运行之后");
    }
    @AfterReturning(value = "getAllPath()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.debug("方法正常结束");
    }
    @AfterThrowing(value = "getAllPath()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        log.debug("方法异常结束");
    }
}
