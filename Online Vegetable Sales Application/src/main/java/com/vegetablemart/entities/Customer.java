package com.vegetablemart.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    
       @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Address> customerAddress;

    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Orders> ordersList;

   @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<BillingDetails> billingDetailsList;


    @OneToOne(mappedBy = "customer")
    private Cart cart;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Feedback> feedbackList = new ArrayList<>();

    
   @NotBlank(message = "Customer name can't be null/blank, Please provide a valid name first!")
    private String name;
    
    @Pattern(regexp = "[0-9]{10}", message = "Invalid mobile number. Please provide a 10-digit number.")
    private String mobileNumber;
    @Column(unique = true)
    @Email
    private String emailId;
    
    private String password;
    private String confirmPassword;

    private String role;

}
