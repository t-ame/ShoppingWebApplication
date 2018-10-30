package com.java.service;

//import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
//import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class LoggingService {

	Logger logger = Logger.getLogger(LoggingService.class);

	@Around(value = "execution(public com.java.controller.ProductController.* (..))")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {

		logger.info("Entering method " + pjp.getSignature());
		Object o = pjp.proceed();
		logger.info("Exiting method " + pjp.getSignature());
		return o;
	}

//	@AfterReturning(returning="returnValue", pointcut="within(com.java.demo.CacheDemoController)")
//	public String logCacheController(String returnValue, JoinPoint joinpoint, HttpServletResponse response) {
//		logger.debug("Header value for cache-control : "+ response.getHeader("cache-control"));
//		return returnValue;
//	}
	
}



