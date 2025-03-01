package com.herbivore.demo.myapp.aspect;

import org.aspectj.lang.annotation.Pointcut;


public interface PointcutDeclarations {
	@Pointcut("execution(* com.herbivore..dao.*.*(..))")
	default void forDaoPackage() {}

	@Pointcut("execution(* com.herbivore..dao.*.find*(..))")
	default void find() {}

	@Pointcut("execution(* com.herbivore..dao.*.update*(..))")
	default void update() {}

	@Pointcut("forDaoPackage() && !(find() || update())")
	default void forDaoPackageNoFindUpdate() {}

	@Pointcut("execution(!float com.herbivore..dao.*.selectAccounts(..))")
	default void selectAccounts() {}

	@Pointcut("execution(* com.herbivore..service.*.getFortune(..))")
	default void getFortune() {}
}
