package com.vegetablemart.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vegetablemart.entities.Address;

import com.vegetablemart.entities.Customer;

import com.vegetablemart.exceptions.CustomerException;

import com.vegetablemart.repository.CustomerRepository;
import com.vegetablemart.service.CustomerService;



public class CustomerServiceImpl implements CustomerService{

	@Autowired
	 CustomerRepository Crepo;
	
	
	@Override
	public Customer addCustomer(Customer customer, Integer customerId) throws CustomerException {
	    Optional<Customer> customerOptional = Crepo.findById(customerId);

	    if (customerOptional.isPresent()) {
	        throw new CustomerException("Customer is already present with Id: " + customerId);
	    }

	    List<Address> customerAddress = customer.getCustomerAddress();
	    for (Address address : customerAddress) {
	        address.setCustomer(customer);
	    }

	   return Crepo.save(customer);
	}

	
	
	@Override
	public List<Customer> viewCustomerAll() throws CustomerException {
	    List<Customer> customerList = Crepo.findAll();
	    
	    if (!customerList.isEmpty()) {
	        return customerList;
	    }
	    
	    throw new CustomerException("No customers found!");
	}
	
	
	
	@Override
	public Customer deleteCustomer(Integer customerId) throws CustomerException {

	    Optional<Customer> cusOptional = Crepo.findById(customerId);

	    if (cusOptional.isPresent()) {
	        Customer existingCustomer = cusOptional.get();
	        Crepo.delete(existingCustomer);
	        return existingCustomer;
	    }
	    throw new CustomerException("No customer found!");
	}

	
	
	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
	    Optional<Customer> existingCustomer = Crepo.findById(customerId);
	    
	    if (!existingCustomer.isPresent()) {
	        throw new CustomerException("No customer exists with this customerId: " + customerId);
	    }
	    
	    return existingCustomer.get();
	}

	
	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
	    Optional<Customer> optional = Crepo.findById(customer.getCustomerId());

	    if (optional.isPresent()) {
	        Customer existingCustomer = optional.get();
	        
	        // Update customer details
	        existingCustomer.setName(customer.getName());
	        existingCustomer.setMobileNumber(customer.getMobileNumber());
	        existingCustomer.setEmailId(customer.getEmailId());
	        existingCustomer.setPassword(customer.getPassword());
	        existingCustomer.setConfirmPassword(customer.getConfirmPassword());
	        existingCustomer.setRole(customer.getRole());
	        
	        // Save and return the updated customer
	        return Crepo.save(existingCustomer);
	    } else {
	        throw new CustomerException("No customer found!");
	    }
	}


}
