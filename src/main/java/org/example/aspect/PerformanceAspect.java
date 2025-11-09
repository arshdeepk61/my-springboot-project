package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
    private static final Logger log = LoggerFactory.getLogger(PerformanceAspect.class);

    // 🔹 Pointcut: monitor all methods in controller, service, and repository packages
    @Pointcut("execution(* org.example.controller..*(..)) || " +
            "execution(* org.example.service..*(..)) || " +
            "execution(* org.example.repository..*(..))")
    public void applicationPackages() {}
        @Before("applicationPackages()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("➡️  Entering Method " + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("➡️  Method: " + joinPoint.getSignature().getName());
    }

    // 🔹 Runs after each controller method returns normally
    @AfterReturning(pointcut = "applicationPackages()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("✅  Exiting Method: " + joinPoint.getSignature().getName());
        System.out.println("✅  Returned: " + result);
    }

    // 🔹 Around advice to measure performance
    @Around("applicationPackages()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;

            if (duration > 2000) { // ⚠️ mark slow calls (e.g., > 2 sec)
                log.warn("⚠️ SLOW METHOD: {} executed in {} ms",
                        joinPoint.getSignature(), duration);
            } else {
                log.info("⏱ {} executed in {} ms",
                        joinPoint.getSignature(), duration);
            }
        }

        return result;
    }
}
