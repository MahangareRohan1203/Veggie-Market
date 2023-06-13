package com.vegetablemart.entities;

import com.vegetablemart.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private BillingDetails billingDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private List<Vegetables> vegetablesList;

    private Integer totalAmount;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;


}
