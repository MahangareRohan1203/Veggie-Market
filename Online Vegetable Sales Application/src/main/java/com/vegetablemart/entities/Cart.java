package com.vegetablemart.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @OneToOne
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Vegetables> vegetablesList;


}
