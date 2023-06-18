package com.vegetablemart.repository;

import com.vegetablemart.entities.CartVegetable;
import com.vegetablemart.entities.CartVegetableId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;

@Repository
public interface CartVegetableRepository extends JpaRepository<CartVegetable, CartVegetableId> {

}
