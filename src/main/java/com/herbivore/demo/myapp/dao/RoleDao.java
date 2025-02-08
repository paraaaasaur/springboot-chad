package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Role;

public interface RoleDao {

	Role findRoleByName(String theRoleName);
	
}
