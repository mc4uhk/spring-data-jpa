package hk.mc4u.backend.service;

import java.lang.reflect.InvocationTargetException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SomeAspect {

	@Around("execution(* doSomethingElse(..))")
	public void aroundDoSomethingElse(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("Beginning execution for " + proceedingJoinPoint.getSignature().getName());
		proceedingJoinPoint.proceed();
		System.out.println("After execution for " + proceedingJoinPoint.getSignature().getName());
	}	
	
	@Before("execution(* doSomething(..))")
	public void beforeDoSomething(JoinPoint joinpoint)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		System.out.println("Before execution for " + joinpoint.getSignature().getName());
	}

}
