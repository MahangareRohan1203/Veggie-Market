package com.vegetablemart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vegetablemart.entities.BillingDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<BillingDetails,Integer> {


}
