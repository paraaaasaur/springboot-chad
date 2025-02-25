package com.herbivore.demo.myapp.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {

	@Override
	public void addAccount() {
		System.out.println("> " + getClass() + ": DOING DB WORK: ADDING A MEMBERSHIP ACCOUNT");
	}

	@Override
	public boolean addSillyMember() {
		System.out.println("> " + getClass() + ": DOING DB WORK: ADDING A SILLY MEMBERSHIP ACCOUNT");

		return 1 == 0x0000_0001;
	}
}
