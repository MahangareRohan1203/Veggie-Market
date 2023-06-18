package com.vegetablemart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vegetablemart.enums.TransactionMode;
import com.vegetablemart.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billingId;

    @OneToOne
    private Orders orders;

    @OneToOne
    private Address address;

    @Column(nullable = false)
    private Double totalAmount;

    @Enumerated(value = EnumType.STRING)
    private TransactionMode transactionMode;

    private LocalDateTime transactionDateTime;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;


}
