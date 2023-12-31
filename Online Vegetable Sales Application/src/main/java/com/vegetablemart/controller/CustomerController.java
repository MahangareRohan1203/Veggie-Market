package com.vegetablemart.controller;

import com.vegetablemart.entities.Customer;
import com.vegetablemart.exceptions.CustomerException;
import com.vegetablemart.exceptions.FeedBackException;
import com.vegetablemart.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vegiee")
public class CustomerController {



    @Autowired
    private CustomerService customerService;


    @PostMapping("/user/customers")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
        // Add Customer using the provided service method
        System.out.println(customer.getCustomerAddress());
        Customer custmer2 = customerService.addCustomer(customer);

        return new ResponseEntity<Customer>(custmer2, HttpStatus.CREATED);
    }


    @PutMapping("/user/customer/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws CustomerException {
        // Update the Customer using the provided service method
        Customer custmer2 = customerService.updateCustomer(customer);

        return new ResponseEntity<Customer>(custmer2, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/user/customer/delete/{customerId}")
    public ResponseEntity<Customer> deleteFeedback(@PathVariable("customerId") Integer customerId) throws CustomerException {
        // Delete the Customer using the provided service method
        Customer custmer2 = customerService.deleteCustomer(customerId);

        return new ResponseEntity<Customer>(custmer2, HttpStatus.ACCEPTED);
    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> viewCustomer(@PathVariable("customerId") Integer customerId) throws FeedBackException {
        // Retrieve a specific customer using the provided service method
        Customer custmer2 = customerService.viewCustomer(customerId);

        return new ResponseEntity<Customer>(custmer2, HttpStatus.ACCEPTED);
    }

    @GetMapping("/customer/all")
    public ResponseEntity<List<Customer>> viewCustomerAll() throws FeedBackException {
        // Retrieve all customer using the provided service method
        List<Customer> custmer2 = customerService.viewCustomerAll();

        return new ResponseEntity<List<Customer>>(custmer2, HttpStatus.ACCEPTED);
    }

}
