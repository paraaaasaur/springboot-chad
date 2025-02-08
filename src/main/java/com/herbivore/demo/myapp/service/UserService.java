package com.herbivore.demo.myapp.service;

import com.herbivore.demo.myapp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

}
