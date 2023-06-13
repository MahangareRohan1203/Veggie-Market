package com.vegetablemart.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Address> customerAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Orders> ordersList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<BillingDetails> billingDetailsList;


    @OneToOne(mappedBy = "customer")
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Feedback> feedbackList = new ArrayList<>();

    private String name;
    private String mobileNumber;
    @Column(unique = true)
    @Email
    private String emailId;
    private String password;
    private String confirmPassword;

    private String role;

}
