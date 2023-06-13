package com.vegetablemart.entities;

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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Cart> cartList = new ArrayList<>();

    @ManyToOne
    private Orders orders;

    private String name;
    private String image;
    private String type;
    private Integer price;
    private Integer quantity;
}
