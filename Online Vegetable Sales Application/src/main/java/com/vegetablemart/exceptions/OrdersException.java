package com.vegetablemart.exceptions;

public class OrdersException extends RuntimeException {
    public OrdersException() {
    }

    public OrdersException(String message) {
        super(message);
    }
}
