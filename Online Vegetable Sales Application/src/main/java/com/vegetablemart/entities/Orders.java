package com.vegetablemart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vegetablemart.dto.VegetableDTO;
import com.vegetablemart.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @JsonIgnore
    @ManyToOne
    private Customer customer;

    @JsonIgnore
    @OneToOne
    private BillingDetails billingDetails;

//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "orders")
//    private List<Vegetables> vegetablesList;
    @ElementCollection
    private List<VegetableDTO> vegetableList;

    private Integer totalAmount;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDateTime;

}
