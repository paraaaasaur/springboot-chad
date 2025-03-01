package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.model.Account;

import java.time.Instant;
import java.util.List;

public interface AccountDAO {

	void addAccount(Account account, boolean vipFlag);

	int doWork();

	String findName(int id);

	Instant findDob(int id);

	void updateName(int id, String name);

	void updateDob(int id, Instant dob);

	List<Account> selectAccounts();

	default void selectAccounts(int dummyParam) {
		System.out.println("> Testing if @AfterReturning works on a void method");
	}

	List<Account> selectAccounts(boolean trueForFreeMembership);
}
