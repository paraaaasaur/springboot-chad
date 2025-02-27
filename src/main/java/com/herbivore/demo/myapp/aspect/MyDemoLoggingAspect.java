package com.herbivore.demo.myapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3)
public class MyDemoLoggingAspect implements PointcutDeclarations {

	@Before("forDaoPackageNoFindUpdate()")
	public void beforeAddAccountAdvice() {
		System.out.println("\n@Order(3) >>>>> Executing @Before advice target method<<<<<");
	}

}