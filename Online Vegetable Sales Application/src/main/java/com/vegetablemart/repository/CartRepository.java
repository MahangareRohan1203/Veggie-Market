package com.vegetablemart.repository;

import com.vegetablemart.entities.Cart;
import com.vegetablemart.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByCustomer(Customer customer);

}
