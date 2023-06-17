package com.vegetablemart.serviceimpl;

import com.vegetablemart.entities.Customer;
import com.vegetablemart.entities.User;
import com.vegetablemart.exceptions.CustomerException;
import com.vegetablemart.exceptions.LoginExceptions;
import com.vegetablemart.repository.CustomerRepository;
import com.vegetablemart.repository.LoginRepository;
import com.vegetablemart.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public User validateLogin(User user) {
        System.out.println(user.getUserName()+" => email");
        // TODO: Validate the Username, Password and Role from Customer repo
        Customer existingCustomer = customerRepository.findByEmailId(user.getUserName()).orElseThrow(()-> new CustomerException("email not exist "));

        if(!existingCustomer.getPassword().equals(user.getPassword())) throw new LoginExceptions("Invalid Credentials");

        User isExist = loginRepository.findByUserName(user.getUserName());
        if(isExist != null) throw new LoginExceptions("User already Logged in");
        else {
            if(!user.getRole().equals(existingCustomer.getRole())) throw new LoginExceptions("You are not using valid role for login");
            isExist = new User();
            isExist.setUserName(user.getUserName());
            isExist.setRole(user.getRole());
            isExist.setCustomer(existingCustomer);
            isExist = loginRepository.save(isExist);
        }
        isExist.setUserId(existingCustomer.getCustomerId());
        return isExist;
    }

    @Override
    public boolean logOut(Integer userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow();
        User user = loginRepository.findByUserName(customer.getEmailId());
        if(user == null) throw new LoginExceptions("User is not Logged in");
        loginRepository.delete(user);
        return true;
    }
}
