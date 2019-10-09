package com.ang.firstweb.aop.problem;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
@Aspect
@Component
@EnableAspectJAutoProxy(exposeProxy=true)
public class MyAspect {
    @Pointcut("execution(* com.ang.firstweb.aop.problem.MyBusiness.methodA(..)) || " +
            " execution(* com.ang.firstweb.aop.problem.MyBusiness.methodB(..))")
    public void pc1(){};

    @Around("pc1()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        long st = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapse = System.currentTimeMillis() - st;
        System.out.println(methodName+" 花费 "+elapse);
        return result;
    }


}
