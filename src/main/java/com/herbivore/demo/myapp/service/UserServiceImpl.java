package com.herbivore.demo.myapp.service;

import com.herbivore.demo.myapp.dao.AccountDAO;
import com.herbivore.demo.myapp.dao.MembershipDAO;
import com.herbivore.demo.myapp.model.Account;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserServiceImpl implements UserService {

	private AccountDAO accountDAO;

	private MembershipDAO membershipDAO;

	protected UserServiceImpl(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		this.accountDAO = accountDAO;
		this.membershipDAO = membershipDAO;
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
	}
}
