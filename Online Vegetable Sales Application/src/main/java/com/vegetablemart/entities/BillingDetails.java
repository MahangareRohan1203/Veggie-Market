package com.vegetablemart.entities;

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

    @ManyToOne
    private Customer customer;

    @OneToOne
    private Address address;

    private String transactionMode;

    private LocalDateTime transactionDateTime;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;


}
