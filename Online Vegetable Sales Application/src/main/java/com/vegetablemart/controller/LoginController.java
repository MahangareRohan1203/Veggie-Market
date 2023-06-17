package com.vegetablemart.controller;

import com.vegetablemart.entities.User;
import com.vegetablemart.serviceimpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/veggieMarket")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public ResponseEntity<User> loginUserHandler(@RequestBody User user) {
        // TODO: Use Authentication and create User and save it in Repository
//        System.out.println("Authentication: => "+ httpRequest.getParameter("username"));
//        System.out.println("Authentication Name: => "+httpRequest.getParameter("password"));
//        System.out.println("Role : => "+ httpRequest.getParameter("role"));
//        System.out.println(httpRequest);
        System.out.println("User in controller"+ user.getUserName());
        User logIn = loginService.validateLogin(user);
        return new ResponseEntity<>(logIn, HttpStatus.OK);
    }

    @GetMapping("/logout/{userId}")
    public ResponseEntity<String> logOutUserHandler(@RequestParam Integer userId) {
        //TODO: Take Proper Details
        loginService.logOut(userId);
        return new ResponseEntity<>("Log out successful",HttpStatus.OK);
    }
}
