package com.vegetablemart.exceptions;

import com.vegetablemart.entities.Cart;

public class CartException extends RuntimeException {
    public CartException() {

    }
    public CartException(String message) {
        super(message);
    }
}
