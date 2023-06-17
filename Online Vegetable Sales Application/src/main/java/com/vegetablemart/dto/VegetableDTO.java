package com.vegetablemart.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class VegetableDTO {
    private Integer vegetableDtoId;
    private String vegetableName;
    private Integer VegetableQuantity;
    private Integer vegetableDtoPrice;
    private String vegetableDtoType;

}
