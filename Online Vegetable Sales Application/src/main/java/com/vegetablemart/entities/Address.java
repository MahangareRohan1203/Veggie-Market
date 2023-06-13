package com.vegetablemart.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @ManyToOne
    private Customer customer;


    private String flatNo;
    private String buildingName;
    private String area;
    private String city;
    private String state;
    private String pincode;

}
