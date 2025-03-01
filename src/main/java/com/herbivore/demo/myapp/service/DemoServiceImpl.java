package com.herbivore.demo.myapp.service;

import com.herbivore.demo.myapp.dao.AccountDAO;
import com.herbivore.demo.myapp.dao.MembershipDAO;
import com.herbivore.demo.myapp.model.Account;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static com.herbivore.demo.myapp.util.Trivial.aqtn;
import static io.github.paraaaasaur.util.Toolbox.cyan;
import static io.github.paraaaasaur.util.Toolbox.red;

@Service
public class DemoServiceImpl implements DemoService {

	private final AccountDAO accountDAO;

	private final MembershipDAO membershipDAO;

	private final TrafficFortuneService trafficFortuneService;

	protected DemoServiceImpl(AccountDAO accountDAO, MembershipDAO membershipDAO, TrafficFortuneService trafficFortuneService) {
		this.accountDAO = accountDAO;
		this.membershipDAO = membershipDAO;
		this.trafficFortuneService = trafficFortuneService;
	}

//	@Transactional
	@Override
	public void demoTheBeforeAdvice() {

		Account tempAccount = new Account();
		tempAccount.setName("Jane Doe");
		tempAccount.setLevel("Over 9000");
		accountDAO.addAccount(tempAccount, true);
		accountDAO.doWork();

		Instant dob = accountDAO.findDob(22);
		String name = accountDAO.findName(22);

		accountDAO.updateDob(22, Instant.parse("2002-04-22T10:10:00.00Z"));
		accountDAO.updateName(22, "Heavens sound");

		membershipDAO.addAccount();
		membershipDAO.addSillyMember();

		aqtn();
	}

//	@Transactional
	@Override
	public void demoTheAfterReturnAdvice() {
		List<Account> accounts = accountDAO.selectAccounts();
//		accountDAO.selectAccounts(0);
		System.out.println("\n\n> Main program: demoTheAfterReturnAdvice()");

		System.out.println("> Post-processed result: ");
		accounts.forEach(System.out::println);

		aqtn();
	}

//	@Transaction
	@Override
	public void demoTheAfterThrowingAdvice() {
		try {
			accountDAO.selectAccounts();
		} catch (Exception e) {
			System.err.println(e.getClass().getSimpleName() + ": " + e.getMessage());
		}

		aqtn();
	}

//	@Transactional
	@Override
	public void demoTheAfterAdvice() {
		IntStream.range(0, 10)
				.forEach(i -> {
					boolean coinFlip = ThreadLocalRandom.current().nextBoolean();
					try {
						accountDAO.selectAccounts(coinFlip);
						System.out.println(cyan("SUCCESS"));
					} catch (Exception e) {
						System.out.println(cyan("FAILURE"));
						System.out.println(red(e.getClass().getSimpleName() + ": " + e.getMessage()));
//						System.err.flush();
					}
				});

		aqtn();
	}

	//	@Transactional
	@Override
	public void demoTheAroundAdvice() {
		System.out.println("\n> Main program: demoTheAroundAdvice");

		System.out.println("> Calling getFortune()");

		String fortune = trafficFortuneService.getFortune();

		System.out.println("> My fortune is: " + fortune);

		aqtn();
	}
}
