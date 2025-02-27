package com.herbivore.demo.myapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyCloudLogAsyncAspect implements PointcutDeclarations {

	@Before("forDaoPackageNoFindUpdate()")
	public void logToCloudAsync() {
		System.out.println("\n@Order(2) ----- Logging to cloud in async fashion -----");
	}
}
