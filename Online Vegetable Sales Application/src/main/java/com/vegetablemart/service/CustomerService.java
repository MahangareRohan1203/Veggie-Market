package com.vegetablemart.service;

import java.util.List;

import com.vegetablemart.entities.Customer;
import com.vegetablemart.exceptions.CustomerException;

public interface CustomerService {
	
	
	public Customer addCustomer(Customer customer) throws CustomerException;
	
	public Customer deleteCustomer(Integer customerId ) throws CustomerException;
    
	
	public List<Customer> viewCustomerAll() throws CustomerException;
	
	
	public Customer viewCustomer(Integer customerId) throws CustomerException ;
	
	
	public Customer updateCustomer(Customer customer) throws CustomerException ;
	
}
