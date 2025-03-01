package com.herbivore.demo.myapp.aspect;

import com.herbivore.demo.myapp.model.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

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

	@AfterReturning(pointcut = "selectAccounts()", returning = "rawResult")
	public void afterReturningSelectAccountsAdvice(
			JoinPoint joinPoint,
			List<Account> rawResult
	) {
		System.out.println("\n@Order(3) >>>>> Executing @AfterReturning advice <<<<<");

		// Display metadata
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		System.out.println("> Method Signature: " + methodSig);
		String methodName = methodSig.toShortString();
		System.out.println("> Executing @AfterReturning advice on: " + methodName);


		// Make use of returning value
		if (rawResult != null) {
			System.out.println("> Raw Result: ");
			rawResult.forEach(System.out::println);
		}

		// Post-Process return value
		// Direct mutation: has effect
		tamperData(rawResult);

		// Assign new reference: no effect
//		rawResult = processedObject;
		rawResult = null;

	}

	private void tamperData(List<Account> rawResult) {
		if (rawResult == null) return;

		rawResult.forEach(account -> {
			account.setName("foo");
			account.setLevel("bar");
		});
	}

	@AfterThrowing(pointcut = "selectAccounts()", throwing = "rot")
	public void afterThrowingSelectAccountsAdvice(
			JoinPoint joinPoint,
			Throwable rot // readonly throwable
	) {
		System.out.println("\n@Order(3) >>>>> Executing @AfterThrowing advice <<<<<");

		// Display metadata
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		System.out.println("> Method Signature: " + methodSig);
		String methodName = methodSig.toShortString();
		System.out.println("> Executing @AfterReturning advice on: " + methodName);

		// Log the throwable
		System.err.println("> Throwable Type: " + rot.getClass().getSimpleName());
		System.err.println("> Error message: " + rot.getMessage());
	}

	@After("selectAccounts()")
	public void afterSelectAccountsAdvice(JoinPoint joinPoint) {
		System.out.println("\n@Order(3) >>>>> Executing @After (finally) advice <<<<<");

		// Display metadata
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		System.out.println("> Method Signature: " + methodSig);
		String methodName = methodSig.toShortString();
		System.out.println("> Executing @AfterReturning advice on: " + methodName);
	}

	@Around("getFortune()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("\n@Order(3) >>>>> Executing @Around advice <<<<<");

		// Display metadata
		MethodSignature methodSig = (MethodSignature) proceedingJoinPoint.getSignature();
		System.out.println("> Method Signature: " + methodSig);
		String methodName = methodSig.toShortString();
		System.out.println("> Executing @AfterReturning advice on: " + methodName);

		long begin = System.nanoTime();

		// ↑↑↑↑↑ Before ↑↑↑↑↑
		Object result = proceedingJoinPoint.proceed();
		// ↓↓↓↓↓ After ↓↓↓↓↓

		long end = System.nanoTime();

		long duration = end - begin;
		System.out.println("Duration: " + duration / 1_000_000d + "(ms)" );

//		System.out.println(result);


		return "";
	}

}