package com.herbivore.demo.myapp.aspect;

import org.aspectj.lang.annotation.Pointcut;

public interface PointcutDeclarations {

	@Pointcut("execution(* com.herbivore..controller.*.*(..))")
	default void controllerPkg() {}

	@Pointcut("execution(* com.herbivore..dao.*.*(..))")
	default void daoPkg() {}

	@Pointcut("execution(* com.herbivore..service.*.*(..))")
	default void servicePkg() {}

	@Pointcut("controllerPkg() || daoPkg() || servicePkg()")
	default void appFlow() {}
}
