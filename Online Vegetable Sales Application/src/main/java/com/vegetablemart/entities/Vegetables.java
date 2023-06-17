package com.vegetablemart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class Vegetables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vegetableId;

//    @ManyToMany(cascade = CascadeType.ALL)
//    private List<Cart> cartList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "vegetable")
    private List<CartVegetable> cartVegetables = new ArrayList<>();

//    @JsonIgnore
//    @ManyToMany(cascade = CascadeType.ALL)
//    private List<Orders> orders;
    
    
    private String name;
    private String image;
    private String type;
    private Integer price;
    private Integer quantity;
    private boolean isDeleted = false;
}
