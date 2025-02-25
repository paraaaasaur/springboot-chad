package com.herbivore.demo.myapp.service;

import com.herbivore.demo.myapp.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private AccountDAO accountDAO;

	@Autowired
	protected UserServiceImpl(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

//	@Transactional
	@Override
	public void demoTheBeforeAdvice() {

		accountDAO.addAccount();

		System.out.println("(Let's call accountDAO.addAccount() again)");

		accountDAO.addAccount();
	}


}
