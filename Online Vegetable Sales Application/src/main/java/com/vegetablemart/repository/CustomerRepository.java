package com.vegetablemart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vegetablemart.entities.Customer;


public interface CustomerRepository  extends JpaRepository<Customer, Integer>{

}
