package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {

	@Override
	public void addAccount(Account account) {
		System.out.println("> " + getClass() + ": DOING DB WORK: ADDING AN ACCOUNT");
	}
}
