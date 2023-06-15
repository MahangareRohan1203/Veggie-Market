package com.vegetablemart.entitiesDto;

import com.vegetablemart.entities.Address;
import com.vegetablemart.enums.TransactionMode;
import com.vegetablemart.enums.TransactionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BillingDetailsDto {

    @OneToOne
    private Address address;

    @Column(nullable = false)
    private double totalAmount;

    @Enumerated(value = EnumType.STRING)
    private TransactionMode transactionMode;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;
}
