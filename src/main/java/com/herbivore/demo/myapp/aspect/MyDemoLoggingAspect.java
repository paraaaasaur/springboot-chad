package com.herbivore.demo.myapp.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@Aspect
public class MyDemoLoggingAspect {
//	private static final String daoPackage = "execution(* com.herbivore..dao.*.*(..))";
	private static final String[] ANIMAL_EMOJIS = {"🐶", "🐱", "🐭", "🐹", "🐰", "🦊", "🐻", "🐼", "🐨", "🐯", "🦁", "🐮", "🐷", "🐸", "🐵", "🦄", "🐔", "🐧", "🐦", "🐤", "🦆", "🦅", "🦉", "🦇", "🐺", "🐗", "🐴", "🦓", "🦌", "🐂", "🐃", "🐄", "🐏", "🐑", "🐐", "🐪", "🐫", "🦙", "🦒", "🐘", "🦏", "🦛", "🐭", "🐹", "🐀", "🐁", "🐿️", "🦔", "🦇", "🐉", "🐲", "🐍", "🦎", "🦂", "🦖", "🦕", "🐙", "🦑", "🦀", "🐡", "🐠", "🐟", "🐬", "🐳", "🐋", "🦈", "🐊", "🐢", "🐸"};


	@Pointcut("execution(* com.herbivore..dao.*.*(..))")
	private void forDaoPackage() {}

	@Pointcut("execution(* com.herbivore..dao.*.find*(..))")
	private void find() {}

	@Pointcut("execution(* com.herbivore..dao.*.update*(..))")
	private void update() {}

	@Pointcut("forDaoPackage() && !(find() || update())")
	private void forDaoPackageNoFindUpdate() {}

//	@Before(daoPackage)
//	@Before("forDaoPackage()")
	@Before("forDaoPackageNoFindUpdate()")
	public void beforeAddAccountAdvice() {
		System.out.println("\n>>>>> Executing @Before advice target method<<<<<");
	}

	// Reuse pointcut
//	@After("forDaoPackage()")
	@After("forDaoPackageNoFindUpdate()")
	public void performFabulousApiAnalytics() {
		String randomEmoji = ANIMAL_EMOJIS[ThreadLocalRandom.current().nextInt(ANIMAL_EMOJIS.length)];
		System.out.printf("≈≈≈≈≈ Performing API analytics%s ≈≈≈≈≈\n", randomEmoji);
	}
}