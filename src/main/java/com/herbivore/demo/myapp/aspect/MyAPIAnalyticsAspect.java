package com.herbivore.demo.myapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Aspect
@Component
@Order(1)
public class MyAPIAnalyticsAspect implements PointcutDeclarations {
	private static final String[] ANIMAL_EMOJIS = {"🐶", "🐱", "🐭", "🐹", "🐰", "🦊", "🐻", "🐼", "🐨", "🐯", "🦁", "🐮", "🐷", "🐸", "🐵", "🦄", "🐔", "🐧", "🐦", "🐤", "🦆", "🦅", "🦉", "🦇", "🐺", "🐗", "🐴", "🦓", "🦌", "🐂", "🐃", "🐄", "🐏", "🐑", "🐐", "🐪", "🐫", "🦙", "🦒", "🐘", "🦏", "🦛", "🐭", "🐹", "🐀", "🐁", "🐿️", "🦔", "🦇", "🐉", "🐲", "🐍", "🦎", "🦂", "🦖", "🦕", "🐙", "🦑", "🦀", "🐡", "🐠", "🐟", "🐬", "🐳", "🐋", "🦈", "🐊", "🐢", "🐸"};

	@Before("forDaoPackageNoFindUpdate()")
	public void performFabulousApiAnalytics() {
		String randomEmoji = ANIMAL_EMOJIS[ThreadLocalRandom.current().nextInt(ANIMAL_EMOJIS.length)];
		System.out.printf("@Order(1) ≈≈≈≈≈ Performing API analytics%s  ≈≈≈≈≈\n", randomEmoji);
	}
}
