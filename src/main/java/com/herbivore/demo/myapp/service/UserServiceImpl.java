package com.herbivore.demo.myapp.service;

import com.herbivore.demo.myapp.dao.AccountDAO;
import com.herbivore.demo.myapp.dao.MembershipDAO;
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

		accountDAO.addAccount();

		membershipDAO.addAccount();

		membershipDAO.addSillyMember();
	}


}
