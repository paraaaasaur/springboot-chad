package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.model.Account;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

	@Override
	public void addAccount(Account account, boolean vipFlag) {
		System.out.println("> " + getClass() + ": DOING DB WORK: ADDING AN ACCOUNT");
	}

	@Override
	public int doWork() {
		System.out.println("> " + getClass() + ": *work work*");
		return 0;
	}

	@Override
	public String findName(int id) {
		System.out.println("> " + getClass() + ": findName(int id)");
		return "^_^";
	}

	@Override
	public Instant findDob(int id) {
		System.out.println("> " + getClass() + ": findDob(int id)");
		return Instant.EPOCH;
	}

	@Override
	public void updateName(int id, String name) {
		System.out.println("> " + getClass() + ": updateName(int id, String name)");
	}

	@Override
	public void updateDob(int id, Instant dob) {
		System.out.println("> " + getClass() + ": updateDob(int id, Instant dob)");
	}

	@Override
	public List<Account> selectAccounts() {

		List<Account> sampleAccounts = new ArrayList<>();

		sampleAccounts.add(new Account("Jane", "Doe"));
		sampleAccounts.add(new Account("Black", "Jack"));
		sampleAccounts.add(new Account("Kate", "Voegele"));

		return sampleAccounts;
	}
}
