package com.herbivore.demo.myapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyDemoLoggingAspect {

	// pointcut
//	@Before("execution(public void addAccount())")
//	@Before("execution(public void com.herbivore.demo.myapp.dao.AccountDAO.addAccount())")
//	@Before("execution(void add*())")
//	@Before("execution(* add*())")
//	@Before("execution(* add*(com.herbivore.demo.myapp.model.Account))")
//	@Before("execution(* add*(com.herbivore.demo.myapp.model.Account, ..))")
//	@Before("execution(* com.herbivore..add*(..))")
	@Before("execution(* com.herbivore..dao.*.*(..))")
	public void beforeAddAccountAdvice() {
		System.out.println("\n>>>>> Executing @Before advice on addAccount()<<<<<");
	}
}