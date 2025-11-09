//package org.example.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class LoggingAspectService {
//
//    // Pointcut: matches all methods in OrderService
//    @Pointcut("execution(* org.example.service.UserService.*(..))")
//    public void userServiceMethods() {}
//
//    // Advice: runs before matched methods
//    @Before("userServiceMethods()")
//    public void logBefore(JoinPoint joinPoint) {
//        System.out.println("Before method: " + joinPoint.getSignature().getName());
//    }
//
//    // Advice: runs after matched methods
//    @After("userServiceMethods()")
//    public void logAfter(JoinPoint joinPoint) {
//        System.out.println("After method: " + joinPoint.getSignature().getName());
//    }
//}
