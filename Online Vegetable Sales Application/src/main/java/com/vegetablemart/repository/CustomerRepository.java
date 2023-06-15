package com.vegetablemart.repository;




import com.vegetablemart.entities.Customer;
import com.vegetablemart.exceptions.CustomerException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer ,Integer> {
    public Optional<Customer> findByEmailId(String email) throws CustomerException;

}
