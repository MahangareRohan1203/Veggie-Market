package com.vegetablemart.controller;

import com.vegetablemart.entities.Vegetables;
import com.vegetablemart.serviceimpl.VegetablesServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class VegetablesController {

    @Autowired
    private VegetablesServiceImpl vegetablesService;


    @PostMapping("/vegetables")
    public ResponseEntity<Vegetables> addNewVegetablesHandler(@RequestBody @Valid Vegetables vegetables) {
        vegetables = vegetablesService.addNewVegetable(vegetables);
        return new ResponseEntity<>(vegetables, HttpStatus.CREATED);
    }


    @PatchMapping("/vegetables/{vegetableId}")
    public ResponseEntity<Vegetables> updateVegetablesHandler(@PathVariable Integer vegetableId, @RequestBody Vegetables vegetables) {
        vegetables = vegetablesService.updateVegetables(vegetableId, vegetables);
        return new ResponseEntity<>(vegetables, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/vegetables")
    public ResponseEntity<Vegetables> deleteVegetableHandler(@RequestBody Vegetables vegetables) {
        vegetables = vegetablesService.removeVegetables(vegetables);
        return new ResponseEntity<>(vegetables, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/vegetables/all")
    public ResponseEntity<List<Vegetables>> getAllVegetablesHandler() {
        List<Vegetables> vegetablesList = vegetablesService.viewAllVegetables();
        return new ResponseEntity<>(vegetablesList, HttpStatus.OK);
    }

    @GetMapping("/vegetables/available")
    public ResponseEntity<List<Vegetables>> getAllAvailableVegetablesHandler() {
        List<Vegetables> vegetablesList = vegetablesService.viewAllAvailableVegetables();
        return new ResponseEntity<>(vegetablesList, HttpStatus.OK);
    }

    @GetMapping("/vegetables")
    public ResponseEntity<List<Vegetables>> getVegetablesByCategoryHandler(@RequestParam String type){
        List<Vegetables> vegetablesList = vegetablesService.viewAllVegetablesByCategory(type);
        return new ResponseEntity<>(vegetablesList, HttpStatus.OK);
    }

    @GetMapping("/vegetables/{name}")
    public ResponseEntity<List<Vegetables>> getVegetableListByNameHandler(@PathVariable String name){
        List<Vegetables> vegetablesList = vegetablesService.viewVegetablesByName(name);
        return new ResponseEntity<>(vegetablesList, HttpStatus.OK);
    }
}
