package com.quiz.aspect;

import com.quiz.dto.PathDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private final RedisTemplate redisTemplate;

    @Pointcut("execution(*  com.quiz.service.impl.TPathServiceImpl.getPathDtoList(..))")
    public void getPathDtoList() {

    }

    @Before("getPathDtoList()")
    public void before(JoinPoint joinPoint) {
        log.debug("方法运行之前");
    }

    @After("getPathDtoList()")
    public void after(JoinPoint joinPoint) {
        log.debug("方法运行之后");
    }

    @AfterReturning(value = "getPathDtoList()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.debug("方法正常结束");
    }

    @AfterThrowing(value = "getPathDtoList()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        log.debug("方法异常结束");
    }

    /**
     * <p>
     * 可以理解为hook
     * </p>
     *
     * @param pjp 用于获取方法
     * @return 原方法的返回值
     * @throws Throwable 执行原方法可能抛出的异常
     */
    @Around("getPathDtoList()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("环绕通知: " + pjp.getSignature().getName() + "方法执行之前");

        final String key = "quiz::t-path::path-dto-list";
        final ListOperations opsForList = redisTemplate.opsForList();
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            return opsForList.range(key, 0, -1);
        }

        // 执行原方法
        Object result = pjp.proceed();

        // 必须指定泛型[就算是Object],不然存储的是一个元素
        opsForList.leftPushAll(key, (List<PathDto>) result);
        log.debug("环绕通知: " + pjp.getSignature().getName() + "方法执行之后");
        return result;
    }
}
