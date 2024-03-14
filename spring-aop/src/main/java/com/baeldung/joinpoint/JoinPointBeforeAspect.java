package com.baeldung.joinpoint;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

import static java.lang.String.format;

@Aspect
@Component
public class JoinPointBeforeAspect {

    private static final Logger log = Logger.getLogger(JoinPointBeforeAspect.class.getName());

    //* 表示任意返回类型 参数里面的..表示任意多个参数
    @Pointcut("execution(* com.baeldung.joinpoint.ArticleService.getArticleList(..))")
    public void articleListPointcut() {
    }

    @Before("articleListPointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info(
                format("Method %s executed with %s arguments",
                        joinPoint.getStaticPart().getSignature(),
                        Arrays.toString(joinPoint.getArgs())
                )
        );
    }

    @Around("articleListPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("aroundAdvice Method executed before " + format("Method %s executed with %s arguments",
                proceedingJoinPoint.getStaticPart().getSignature(),
                Arrays.toString(proceedingJoinPoint.getArgs())
        ));
        Object proceed = proceedingJoinPoint.proceed();
        log.info("aroundAdvice Method executed after " + format("Method %s executed with %s arguments",
                proceedingJoinPoint.getStaticPart().getSignature(),
                Arrays.toString(proceedingJoinPoint.getArgs())
        ));
        return proceed;
    }
}
