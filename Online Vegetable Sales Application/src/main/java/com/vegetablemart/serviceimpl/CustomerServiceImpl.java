package com.vegetablemart.serviceimpl;


import com.vegetablemart.entities.Cart;
import com.vegetablemart.entities.Customer;
import com.vegetablemart.exceptions.CustomerException;
import com.vegetablemart.repository.CartRepository;
import com.vegetablemart.repository.CustomerRepository;
import com.vegetablemart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository Crepo;


    @Autowired
    private CartRepository cartRepository;

    @Override
    public Customer addCustomer(Customer customer) throws CustomerException {

        if (!customer.getPassword().equals(customer.getConfirmPassword()))
            throw new CustomerException("Both the passwords should be same");
        Optional<Customer> customerOptional = Crepo.findById(customer.getCustomerId());

        if (customerOptional.isPresent()) {
            throw new CustomerException("Customer is already present with Id: " + customer.getCustomerId());
        }
        customer.setCustomerId(null);
        customer.setRole("ROLE_USER");
        customer.setExist(true);

        Cart cart = new Cart();
        cart = cartRepository.save(cart);
        customer.setCart(cart);
        customer = Crepo.save(customer);
        cart.setCustomer(customer);
        cartRepository.save(cart);
        return customer;
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
            existingCustomer.setExist(false);
            Crepo.save(existingCustomer);
            return existingCustomer;
        }
        throw new CustomerException("No customer found!");
    }


    @Override
    public Customer viewCustomer(Integer customerId) throws CustomerException {
        Optional<Customer> existingCustomer = Crepo.findById(customerId);

        if (existingCustomer.isEmpty()) {
            throw new CustomerException("No customer exists with this customerId: " + customerId);
        }

        if (!existingCustomer.get().isExist()) throw new CustomerException("Customer was deleted from database");
        return existingCustomer.get();
    }


    @Override
    public Customer updateCustomer(Customer customer) throws CustomerException {
        Optional<Customer> optional = Crepo.findById(customer.getCustomerId());



	    Optional<Customer> cusOptional = Crepo.findById(customer.getCustomerId());

	   

	

        if (optional.isPresent()) {
            Customer existingCustomer = optional.get();

            // Update customer details
            if (customer.getName() != null) existingCustomer.setName(customer.getName());
            if (customer.getMobileNumber() != null) existingCustomer.setMobileNumber(customer.getMobileNumber());
            if (customer.getEmailId() != null) existingCustomer.setEmailId(customer.getEmailId());
            //TODO: Not Setting the password again
            Crepo.save(existingCustomer);
            return existingCustomer;
        } else {
            throw new CustomerException("No customer found!");
        }
    }



}
