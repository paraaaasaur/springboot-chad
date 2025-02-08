package com.herbivore.demo.myapp.service;

import com.herbivore.demo.myapp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <ol>
 *     <li>{@code findByUserName}: Query user table</li>
 *     <li>{@code loadUserByUsername}: Query DB & Construct {@code UserDetails},
 *     which contains (1) username + (2) password + (3) roles information</li>
 * </ol>
 **/
public interface UserService extends UserDetailsService {

	User findByUserName(String userName);

}
