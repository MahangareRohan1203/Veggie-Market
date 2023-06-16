package com.vegetablemart.service;

import com.vegetablemart.entities.Cart;
import com.vegetablemart.entities.Customer;
import com.vegetablemart.entities.Vegetables;
import com.vegetablemart.exceptions.CartException;

import java.util.List;

public interface CartService {

    public Cart addToCart(Integer customerId, Vegetables vegetables) throws CartException;

    public void increaseQuantity(Integer customerId, Vegetables vegetables, int quantity) throws CartException;


    public void decreseQuantity(Integer VegtableId, int quantity) throws CartException;

    public Cart removeVegetableFromCart(Integer customerId, Vegetables vegetables)throws CartException;


    public String removeAllVegetablesFromCart(Integer cartId)throws CartException;

    public List<Vegetables> viewVegetableList(Integer cartId)throws CartException;


    public List<Vegetables> getAllVegetablesFromCart(Integer customerId);

}