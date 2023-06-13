package com.vegetablemart.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
