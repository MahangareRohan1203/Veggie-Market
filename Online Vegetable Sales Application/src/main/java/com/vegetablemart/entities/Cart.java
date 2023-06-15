package com.vegetablemart.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private Customer customer;

    private double totalPrice;

    private LocalDateTime dateAdded;

    private boolean isPurchased;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Vegetables> vegetablesList;

}
