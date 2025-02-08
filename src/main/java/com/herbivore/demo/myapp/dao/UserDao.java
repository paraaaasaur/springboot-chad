package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
}
