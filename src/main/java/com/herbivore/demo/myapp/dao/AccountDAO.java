package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.model.Account;

import java.time.Instant;

public interface AccountDAO {

	void addAccount(Account account, boolean vipFlag);

	int doWork();

	String findName(int id);

	Instant findDob(int id);

	void updateName(int id, String name);

	void updateDob(int id, Instant dob);
}
