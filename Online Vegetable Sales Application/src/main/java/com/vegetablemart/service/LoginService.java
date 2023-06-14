package com.vegetablemart.service;


import com.vegetablemart.entities.Customer;
import com.vegetablemart.entities.User;

public interface LoginService {
    public User validateLogin(Customer customer);

    public boolean logOut(User user);
}
