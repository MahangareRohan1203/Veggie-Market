package com.vegetablemart.entities;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Feedback {
   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;

    @ManyToOne
    private Customer customer;


    private Integer vegetableId;
    
    @Min(value=1, message="Rating must be in range of 1-5")  
	@Max(value=5, message="Rating must be in range of 1-5")
    private Integer rating;
    
    
    private String comments;
    
    @NotNull(message = "Please provide correct date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDateTime feedbackDateTime;
    
	
}
