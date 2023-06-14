package com.vegetablemart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vegetablemart.entities.BillingDetails;

public interface BillingRepository extends JpaRepository<BillingDetails,Integer> {


}
