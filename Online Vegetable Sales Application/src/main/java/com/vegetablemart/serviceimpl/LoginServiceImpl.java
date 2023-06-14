package com.vegetablemart.serviceimpl;

import com.vegetablemart.entities.Customer;
import com.vegetablemart.entities.User;
import com.vegetablemart.exceptions.LoginExceptions;
import com.vegetablemart.repository.LoginRepository;
import com.vegetablemart.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public User validateLogin(Customer customer) {
        if(customer.getPassword() == null) throw new LoginExceptions("Provide Password");
        if(customer.getEmailId() == null) throw new LoginExceptions("Provide Username");
        if(customer.getRole() == null) throw new LoginExceptions("Provide Username");
        // TODO: Validate the Username, Password and Role from Customer repo

        User isExist = loginRepository.findByUserName(customer.getEmailId());
        if(isExist != null) throw new LoginExceptions("User already Logged in");
        else {
            isExist = new User();
            isExist.setUserName(customer.getEmailId());
            isExist.setRole(customer.getRole());
        }
        return isExist;
    }

    @Override
    public boolean logOut(User user) {
        user = loginRepository.findByUserName(user.getUserName());
        if(user == null) throw new LoginExceptions("User is not Logged in");
        loginRepository.delete(user);
        return true;
    }
}
