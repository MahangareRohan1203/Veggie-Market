package com.vegetablemart.service;


import com.vegetablemart.entities.Customer;
import com.vegetablemart.entities.User;

public interface LoginService {
    public User validateLogin(User user);

    public boolean logOut(Integer userId);
}
