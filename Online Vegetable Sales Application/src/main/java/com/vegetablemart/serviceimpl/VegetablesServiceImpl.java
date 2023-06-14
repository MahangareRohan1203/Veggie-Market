package com.vegetablemart.serviceimpl;

import com.vegetablemart.entities.Vegetables;
import com.vegetablemart.exceptions.VegetablesException;
import com.vegetablemart.repository.VegetablesRepository;
import com.vegetablemart.service.VegetablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VegetablesServiceImpl implements VegetablesService {

    @Autowired
    private VegetablesRepository vegetablesRepository;

    @Override
    public Vegetables addNewVegetable(Vegetables vegetables) {
        Optional<Vegetables> isExist = vegetablesRepository.findById(vegetables.getVegetableId());
        if (isExist.isPresent())
            throw new VegetablesException("Vegetable already exists with given Id: " + vegetables.getVegetableId());
        vegetablesRepository.save(vegetables);
        return vegetables;
    }

    @Override
    public Vegetables updateVegetables(Integer vegetableId, Vegetables vegetables) {
        Vegetables existingVegetables = vegetablesRepository.findById(vegetableId).orElseThrow(() -> new VegetablesException("Vegetable not found to update with vegetable " + vegetableId));
        if (vegetables.getName() != null) existingVegetables.setName(vegetables.getName());
        if (vegetables.getPrice() != null) existingVegetables.setPrice(vegetables.getPrice());
        if (vegetables.getImage() != null) existingVegetables.setImage(vegetables.getImage());
        if (vegetables.getQuantity() != null) existingVegetables.setQuantity(vegetables.getQuantity());

        vegetablesRepository.save(existingVegetables);
        return existingVegetables;
    }

    @Override
    public Vegetables removeVegetables(Vegetables vegetables) {
        Vegetables existingVegetable = vegetablesRepository.findById(vegetables.getVegetableId()).orElseThrow(() -> new VegetablesException("Vegetable with Id: " + vegetables.getVegetableId() + " Not found to remove"));
        if (existingVegetable.isDeleted()) throw new VegetablesException("Vegetable already Deleted. ");
        existingVegetable.setDeleted(true);
        vegetablesRepository.save(existingVegetable);
        return vegetables;
    }

    @Override
    public List<Vegetables> viewAllVegetables() {
        List<Vegetables> vegetablesList = vegetablesRepository.findAll();
        if (vegetablesList.size() == 0) throw new VegetablesException("Vegetable List is Empty.");
        return vegetablesList;
    }

    @Override
    public List<Vegetables> viewAllAvailableVegetables() {
        List<Vegetables> vegetablesList = vegetablesRepository.findByAvailability();
        if (vegetablesList.size() == 0) throw new VegetablesException("Available List is Empty.");
        return vegetablesList;
    }

    @Override
    public List<Vegetables> viewAllVegetablesByCategory(String category) {
        List<Vegetables> vegetablesList = vegetablesRepository.findByType(category);
        if (vegetablesList.size() == 0)
            throw new VegetablesException("Available List is Empty with category: " + category);
        return vegetablesList;
    }

    @Override
    public List<Vegetables> viewVegetablesByName(String name) {
        List<Vegetables> vegetablesList = vegetablesRepository.findByName(name);
        if (vegetablesList.size() == 0) throw new VegetablesException("Available List is Empty with Name : " + name);
        return vegetablesList;
    }
}
