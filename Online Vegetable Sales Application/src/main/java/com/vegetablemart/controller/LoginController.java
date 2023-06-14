package com.vegetablemart.controller;

import com.vegetablemart.entities.Customer;
import com.vegetablemart.entities.User;
import com.vegetablemart.serviceimpl.LoginServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @GetMapping("/login")
    public ResponseEntity<User> loginUserHandler(HttpServletRequest httpRequest){
        // TODO: Use Authentication and create User and save it in Repository
        System.out.println("Authentication: => "+ httpRequest.getParameter("username"));
        System.out.println("Authentication Name: => "+httpRequest.getParameter("password"));
        System.out.println("Role : => "+ httpRequest.getParameter("role"));
        System.out.println(httpRequest);
//        User logIn = loginService.validateLogin(customer);
        return new ResponseEntity<>(new User(), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logOutUserHandler(){
        //TODO: Take Proper Details
        User logIn = new User();
        loginService.logOut(logIn);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
