package com.wf.ivankov.shop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Ivankov_A
 *
 */
@Aspect
@Component
public class ControllerMonitor {

	private static final Logger LOG = LoggerFactory.getLogger(ControllerMonitor.class);
	
	@Pointcut("execution(* com.wf.ivankov.shop.controller.*.*(..))")
	public void restControllerMethods() { }

	@Around("restControllerMethods()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		LOG.debug(">> Starting Controller Method: {}, Args: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
		Object result = joinPoint.proceed();
		LOG.debug("<< Ending Controller Method: {}, Result: {}", joinPoint.getSignature().toShortString(), result);
		return result;
	}
}
