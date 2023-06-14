package com.vegetablemart.exceptions;

public class VegetablesException extends RuntimeException {
    public VegetablesException() {
    }

    public VegetablesException(String message) {
        super(message);
    }
}
