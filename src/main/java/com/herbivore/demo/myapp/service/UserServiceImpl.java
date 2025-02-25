package com.herbivore.demo.myapp.service;

import com.herbivore.demo.myapp.dao.AccountDAO;
import com.herbivore.demo.myapp.dao.MembershipDAO;
import com.herbivore.demo.myapp.model.Account;
import org.springframework.stereotype.Service;

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
		accountDAO.addAccount(tempAccount);

		membershipDAO.addAccount();

		membershipDAO.addSillyMember();
	}


}
