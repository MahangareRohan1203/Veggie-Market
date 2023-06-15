package com.vegetablemart.service;

import com.vegetablemart.entities.Cart;
import com.vegetablemart.entities.Customer;
import com.vegetablemart.entities.Vegetables;
import com.vegetablemart.exceptions.CartException;

import java.util.List;

public interface CartService {
    public Cart generateCartForCustomer(Integer customerId) throws CartException;

    public Cart addToCart(Integer cartId, Integer vegetableId, Integer quantity) throws CartException;

    public void increaseQuantity(Integer VegtableId, int quantity) throws CartException;


    public void decreseQuantity(Integer VegtableId, int quantity) throws CartException;

    public Cart removeVegetableFromCart(Integer vegetableId, Integer cartId)throws CartException;


    public String removeAllVegetablesFromCart(Integer cartId)throws CartException;

    public List<Vegetables> viewVegetableList(Integer cartId)throws CartException;

}