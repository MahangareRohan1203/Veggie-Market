package com.vegetablemart.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CartVegetable")
public class CartVegetable {
    @EmbeddedId
    private CartVegetableId id;

    private Integer quantityForCart;

    @ManyToOne
    @MapsId("cartId")
    private Cart cart;

    @ManyToOne
    @MapsId("vegetableId")
    private Vegetables vegetable;

}

