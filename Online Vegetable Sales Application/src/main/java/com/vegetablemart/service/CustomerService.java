package com.vegetablemart.service;

import com.vegetablemart.entities.Customer;
import com.vegetablemart.exceptions.CustomerException;

import java.util.List;

public interface CustomerService {


    public Customer addCustomer(Customer customer) throws CustomerException;

    public Customer deleteCustomer(Integer customerId) throws CustomerException;


    public List<Customer> viewCustomerAll() throws CustomerException;


    public Customer viewCustomer(Integer customerId) throws CustomerException;


    public Customer updateCustomer(Customer customer) throws CustomerException;

}
