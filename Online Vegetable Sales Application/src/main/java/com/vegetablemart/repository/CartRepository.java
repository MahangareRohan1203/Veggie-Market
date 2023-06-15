package com.vegetablemart.repository;

import com.vegetablemart.entities.Cart;
import com.vegetablemart.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {


    @Query("SELECT c FROM Cart c WHERE c.customer.customerId = :customerId")
    Cart getCartByCustomerId(@Param("customerId") Integer customerId);




}
