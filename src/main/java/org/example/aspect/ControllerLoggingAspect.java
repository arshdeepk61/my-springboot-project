package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ControllerLoggingAspect {

    // 🔹 Pointcut: matches all methods inside com.example.controller package
    @Pointcut("execution(* org.example.controller.*.*(..))")
    public void controllerMethods() {}

    // 🔹 Runs before each controller method
    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("➡️  Entering Controller: " + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("➡️  Method: " + joinPoint.getSignature().getName());
    }

    // 🔹 Runs after each controller method returns normally
    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("✅  Exiting Method: " + joinPoint.getSignature().getName());
        System.out.println("✅  Returned: " + result);
    }

    // 🔹 Runs if a controller method throws an exception
    @AfterThrowing(pointcut = "controllerMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("❌  Exception in Method: " + joinPoint.getSignature().getName());
        System.out.println("❌  Message: " + ex.getMessage());
    }

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("🌀 Around - Starting: " + joinPoint.getSignature());
        System.out.println("🧩 Args: " + Arrays.toString(joinPoint.getArgs()));

        Object result;
        try {
            result = joinPoint.proceed(); // execute the actual method
            System.out.println("🌀 Around - Completed: " + joinPoint.getSignature());
            System.out.println("✅ Result: " + result);
        } catch (Exception ex) {
            System.out.println("❌ Around - Exception: " + ex.getMessage());
            throw ex;
        } finally {
            long duration = System.currentTimeMillis() - start;
            System.out.println("⏱ Execution Time: " + duration + " ms");
        }

        return result;
    }
}
