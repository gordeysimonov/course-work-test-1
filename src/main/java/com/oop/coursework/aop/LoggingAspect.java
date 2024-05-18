package com.oop.coursework.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.oop.coursework.annotation.LogModel)")
    public void modelPointcut() {}

    @Pointcut("@annotation(com.oop.coursework.annotation.LogController)")
    public void controllerPointcut() {}

    @Pointcut("@annotation(com.oop.coursework.annotation.LogService)")
    public void servicePointcut() {}

    @Pointcut("@annotation(com.oop.coursework.annotation.LogRepository)")
    public void repositoryPointcut() {}

    @Before("controllerPointcut()")
    public void logControllerMethodEntry(JoinPoint joinPoint) {
        logger.info("Entering controller method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "controllerPointcut()", returning = "result")
    public void logControllerMethodExit(JoinPoint joinPoint, Object result) {
        logger.info("Exiting controller method: " + joinPoint.getSignature().getName() + ", result: " + toJsonString(result));
    }

    @Before("servicePointcut()")
    public void logServiceMethodEntry(JoinPoint joinPoint) {
        logger.info("Entering service method: " + joinPoint.getSignature().getName() + ", with arguments: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "servicePointcut()", returning = "result")
    public void logServiceMethodExit(JoinPoint joinPoint, Object result) {
        logger.info("Exiting service method: " + joinPoint.getSignature().getName() + ", result: " + result);
    }

    @Before("repositoryPointcut()")
    public void logRepositoryMethodEntry(JoinPoint joinPoint) {
        logger.info("Entering repository method: " + joinPoint.getSignature().getName() +
                ", parameters: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "repositoryPointcut()", returning = "result")
    public void logRepositoryMethodExit(JoinPoint joinPoint, Object result) {
        logger.info("Exiting repository method: " + joinPoint.getSignature().getName() +
                ", result: " + result);
    }

    @Before("modelPointcut()")
    public void logModelMethodEntry(JoinPoint joinPoint) {
        logger.info("Entering model method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "modelPointcut()", returning = "result")
    public void logModelMethodExit(JoinPoint joinPoint, Object result) {
        logger.info("Exiting model method: " + joinPoint.getSignature().getName() + ", result: " + toJsonString(result));
    }

    @AfterReturning(
            pointcut = "execution(* com.oop.coursework.services.*.create*(..)) && args(.., requestBody)",
            returning = "result"
    )
    public void logCreateOperation(JoinPoint joinPoint, Object result, Object requestBody) {
        logger.info("Created entity with data: " + requestBody.toString());
    }

    private String toJsonString(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

}
