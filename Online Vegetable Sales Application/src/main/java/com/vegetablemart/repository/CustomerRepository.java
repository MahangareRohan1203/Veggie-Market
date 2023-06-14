package com.vegetablemart.repository;


import com.vegetablemart.entities.Customer;
import com.vegetablemart.exceptions.CustomerException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer ,Integer> {
    public Optional<Customer> findByEmail(String email) throws CustomerException;
}
