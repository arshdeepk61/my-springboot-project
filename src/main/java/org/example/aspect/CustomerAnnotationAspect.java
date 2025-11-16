package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomerAnnotationAspect {

    private static final Logger logger= LoggerFactory.getLogger(CustomerAnnotationAspect.class);

    @Around("@annotation(org.example.aspect.LogExecution)")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName= joinPoint.getSignature().getName();

        Object[] args=joinPoint.getArgs();

        logger.info("CUSTOM ANNOTATION: Executing method: {} with args: {}", methodName, args);
        long startTime=System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;

            logger.info(" CUSTOM ANNOTATION: Method {} completed in {} ms", methodName, executionTime);

            return result;
        } catch (Exception e) {
            logger.error("📝CUSTOM ANNOTATION: Method {} failed: {}", methodName, e.getMessage());
            throw e;
        }


    }

}
