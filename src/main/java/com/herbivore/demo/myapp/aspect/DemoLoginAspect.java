package com.herbivore.demo.myapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoginAspect implements PointcutDeclarations {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Before("appFlow()")
	public void before(JoinPoint joinPoint) {
		logger.info("===== @Before advice =====");
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		logger.info("> Method: " + methodSig.toShortString());

		for (Object arg : joinPoint.getArgs()) {
			logger.info("  - (" + arg.getClass().getSimpleName() + ") " + arg);
		}
	}

	@AfterReturning(pointcut = "appFlow()", returning = "rawResult")
	public void afterReturning(JoinPoint joinPoint, final Object rawResult) {
		logger.info("===== @AfterReturning advice =====");
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		logger.info("> Method: " + methodSig.toShortString());

		logger.info("  - (" + rawResult.getClass().getSimpleName() + ") " + rawResult);
	}
}
