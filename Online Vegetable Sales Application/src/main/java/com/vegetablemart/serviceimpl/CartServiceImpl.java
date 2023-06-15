package com.vegetablemart.serviceimpl;

import com.vegetablemart.entities.Cart;
import com.vegetablemart.entities.Customer;
import com.vegetablemart.entities.Vegetables;
import com.vegetablemart.exceptions.CartException;
import com.vegetablemart.exceptions.CustomerException;
import com.vegetablemart.repository.CartRepository;
import com.vegetablemart.repository.CustomerRepository;
import com.vegetablemart.repository.VegetablesRepository;
import com.vegetablemart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private VegetablesRepository vegetableRepository;


    @Override
    public Cart generateCartForCustomer(Integer customerId) throws CartException {
        // Check if the customer already has a cart
      Cart existingCustomer = cartRepository.getCartByCustomerId(customerId);
        if (existingCustomer != null) {
            return existingCustomer;
        }
        Optional<Customer> res = customerRepository.findById(customerId);
        if (res.isPresent()) {
            Customer customer = res.get();
            Cart cart = new Cart();
            cart.setCustomer(customer);
            cart.setTotalPrice(0.0);
            cart.setDateAdded(LocalDateTime.now());
            cart.setPurchased(false);
            cart.setVegetablesList(new ArrayList<>());
            // Save the cart
            return cartRepository.save(cart);
        }

        throw new CustomerException("Customer Not found");
    }

    public Cart addToCart(Integer cartId, Integer vegetableId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartException("Cart not found"));

        Vegetables vegetable = vegetableRepository.findById(vegetableId)
                .orElseThrow(() -> new CartException("Vegetable not found"));

        boolean isVegetableInCart = cart.getVegetablesList().contains(vegetable);

        if (isVegetableInCart) {
            throw new CartException("Vegetable already exists in the cart");
        } else {
            cart.getVegetablesList().add(vegetable);
        }

        // Recalculate the total price of the cart
        double totalPrice = calculateTotalPrice(cart);
        cart.setTotalPrice(totalPrice);

        cartRepository.save(cart);
        return cart;
    }

    private double calculateTotalPrice(Cart cart) {
        double totalPrice = 0.0;

        for (Vegetables vegetable : cart.getVegetablesList()) {
            double price = vegetable.getPrice();
            totalPrice += price;
        }

        return totalPrice;
    }


    @Override
    public void increaseQuantity(Integer VegtableId, int quantity) throws CartException {
        Optional<Vegetables> vegetableOpt = vegetableRepository.findById(VegtableId);
        if(vegetableOpt.isPresent()){

            Vegetables  vegetableEntity= vegetableOpt.get();
            int existingQuantity = vegetableEntity.getQuantity();
            int newQuantity = existingQuantity +quantity;
            vegetableEntity.setQuantity(newQuantity);
            vegetableRepository.save(vegetableEntity);

            System.out.println("Successfully Incresed " + quantity);
        }
        throw new CartException("Vegetable Not found");
    }

    @Override
    public void decreseQuantity(Integer vegetable, int quantity) throws CartException {

        Optional<Vegetables> vegetableOpt = vegetableRepository.findById(vegetable);
        if(vegetableOpt.isPresent()){

            Vegetables  vegetableEntity= vegetableOpt.get();
            int existingQuantity = vegetableEntity.getQuantity();
            int newQuantity = existingQuantity -quantity;
            vegetableEntity.setQuantity(newQuantity);
            vegetableRepository.save(vegetableEntity);

            System.out.println("Successfully decreased " + quantity);
        }
        throw new CartException("Vegetable Not found");
    }

    @Override
    public Cart removeVegetableFromCart(Integer vegetableId, Integer cartId) throws CartException {
        Vegetables vegetable = vegetableRepository.findById(vegetableId)
                .orElseThrow(() -> new CartException("Vegetable not found"));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartException("Cart not found"));

        // Remove the vegetable from the cart
        cart.getVegetablesList().remove(vegetable);

        // Save the updated cart in the database
        cartRepository.save(cart);

        System.out.println("Successfully removed the vegetable from the cart.");
        return cart;

    }

    @Override
    public String removeAllVegetablesFromCart(Integer cartId) throws CartException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartException("Cart not found"));

        cart.getVegetablesList().clear();
        // Save the updated cart in the database
        cartRepository.save(cart);

        System.out.println("Successfully removed all vegetables from the cart.");
        return "Successfully removed all vegetables from the cart.";
    }

    @Override
    public List<Vegetables> viewVegetableList(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartException("Cart not found"));

        return cart.getVegetablesList();
    }



}
