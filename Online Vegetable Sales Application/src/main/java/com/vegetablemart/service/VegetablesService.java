package com.vegetablemart.service;

import com.vegetablemart.entities.Vegetables;

import java.util.List;

public interface VegetablesService {
    public Vegetables addNewVegetable(Vegetables vegetables);

    public Vegetables updateVegetables(Integer vegetableId,Vegetables vegetables);

    public Vegetables removeVegetables(Vegetables vegetables);

    public List<Vegetables> viewAllVegetables();

    public List<Vegetables> viewAllAvailableVegetables();

    public List<Vegetables> viewAllVegetablesByCategory(String category);

    public List<Vegetables> viewVegetablesByName(String name);
}
