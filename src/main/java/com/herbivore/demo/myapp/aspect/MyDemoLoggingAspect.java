package com.herbivore.demo.myapp.aspect;

import com.herbivore.demo.myapp.model.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3)
public class MyDemoLoggingAspect implements PointcutDeclarations {

	@Before("forDaoPackageNoFindUpdate()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {
		System.out.println("\n@Order(3) >>>>> Executing @Before advice target method<<<<<");

		// Display method signature
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();

		System.out.println("> Method Signature: " + methodSig);

		// Display method arguments
		for (Object arg : joinPoint.getArgs()) {
			System.out.printf("\t- arg: %s \n", arg.getClass().getSimpleName());

			if (arg instanceof Account account) {
				System.out.printf("\t  Account Name: %s\n", account.getName());
				System.out.printf("\t  Account Level: %s\n", account.getLevel());
			}
		}


	}

}