package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.model.Account;

public interface AccountDAO {

	void addAccount(Account account, boolean vipFlag);

	int doWork();
}
