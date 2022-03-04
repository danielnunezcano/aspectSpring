package com.bitsmuggler.learning.springbootaspect.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class CheckAspect {

  private static final Logger LOGGER = LogManager.getLogger(CheckAspect.class);

  @Before("execution(public String index())")
  public void porPaquete(JoinPoint joinPoint) {
    List<String> splitName = Arrays.asList(joinPoint.getTarget().getClass().getName().split("\\."));
    LOGGER.info("Antes en Alrededor de la ejecución de {}.{}",splitName.get(splitName.size()-1),joinPoint.getSignature().getName());
    LOGGER.info("Después de la ejecución");
  }

  @Before("@annotation(com.bitsmuggler.learning.springbootaspect.aspects.CheckSomething)")
  public void checkSomethingBefore(JoinPoint joinPoint) {
    System.out.println("Before execution - Roles: " + Arrays.toString(getRoles(joinPoint)));
  }

  @AfterReturning(pointcut = "@annotation(com.bitsmuggler.learning.springbootaspect.aspects.CheckSomething)")
  public void checkSomethingAfter(JoinPoint joinPoint) {
    System.out.println("After execution - Roles: " + Arrays.toString(getRoles(joinPoint)));

  }

  @AfterThrowing(pointcut = "@annotation(com.bitsmuggler.learning.springbootaspect.aspects.CheckSomething)", throwing = "ex")
  public void checkSomethingAfterThrowingAnException(JoinPoint joinPoint, Exception ex) {
    System.out.println("After throwing an exception - Roles:" + Arrays.toString(getRoles(joinPoint)) + ex);
  }

  @Around("@annotation(com.bitsmuggler.learning.springbootaspect.aspects.CheckSomething)")
  public Object checkSomethingAround(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("Before in Around execution. - Roles: " + Arrays.toString(getRoles(joinPoint)));
    Object result = joinPoint.proceed();
    System.out.println("After in arround execution");
    return result;
  }


  public String[] getRoles(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    CheckSomething myAnnotation = method.getAnnotation(CheckSomething.class);
    return myAnnotation.roles();
  }
}
