package com.example.demo.aop;

import com.example.demo.springboot2023.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class HelloAspect {

//    @Pointcut("execution(public * com.example.demo..*.*(..))")
//    public void pointcut() {
//    }
//
//
//    @Before("pointcut()")
//    public void before(JoinPoint point){
//        log.info("前置通知");
//    }
//
//    @After("pointcut()")
//    public void after(JoinPoint point) {
//        log.info("后置通知");
//    }
//
//    @Around("pointcut()")
//    public Object around(ProceedingJoinPoint joinPoint) {
//        log.info("环绕通知");
//        try {
//            // 计时
//           // TimeInterval timer = DateUtil.timer();
//            // 执行方法，连接点
//            Object result = joinPoint.proceed();
//            // 查看耗时
//            log.info("耗时：{}", 100);
//            return result;
//        } catch (Throwable throwable) {
//            Result result = new Result();
//            result.fail("服务器繁忙，请稍后再试");
//            return result;
//        }
//    }
//
//    @AfterReturning("pointcut()")
//    public void afterReturning(JoinPoint point) {
//        log.info("返回通知");
//    }
//
//    @AfterThrowing(value = "pointcut()", throwing = "t")
//    public void afterThrowing(JoinPoint point, Throwable t) {
//        log.info("异常通知");
//    }

}
