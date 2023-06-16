package com.vegetablemart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @OneToOne
    @JsonIgnore
    private Customer customer;

    private Double totalPrice;

//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
//    private List<Vegetables> vegetablesList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartVegetable> cartVegetables = new ArrayList<>();

}
