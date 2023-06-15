package com.vegetablemart.repository;

import com.vegetablemart.entities.Vegetables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VegetablesRepository extends JpaRepository<Vegetables, Integer> {

    @Query("Select v from Vegetables v where v.isDeleted = false")
    public List<Vegetables> findByAvailability();

    @Query("Select v from Vegetables v where v.type = :type AND v.isDeleted = false")
    public List<Vegetables> findByType(String type);

    @Query("Select v from Vegetables v where v.name = :name AND v.isDeleted = false")
    public List<Vegetables> findByName(String name);

    public Vegetables findByVegetableId(Integer vegetableId);
}
