package com.vegetablemart.serviceimpl;

import com.vegetablemart.entities.*;
import com.vegetablemart.exceptions.CartException;
import com.vegetablemart.exceptions.CustomerException;
import com.vegetablemart.exceptions.VegetablesException;
import com.vegetablemart.repository.CartRepository;
import com.vegetablemart.repository.CartVegetableRepository;
import com.vegetablemart.repository.CustomerRepository;
import com.vegetablemart.repository.VegetablesRepository;
import com.vegetablemart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private VegetablesRepository vegetableRepository;

    @Autowired
    private CartVegetableRepository cartVegetableRepository;


    public Cart addToCart(Integer customerId, Vegetables vegetables) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerException("Customer not found"));

        Cart cart = cartRepository.findById(customer.getCart().getCartId())
                .orElseThrow(() -> new CartException("Cart not found"));

        Vegetables vegetable = vegetableRepository.findById(vegetables.getVegetableId())
                .orElseThrow(() -> new CartException("Vegetable not found"));

        List<CartVegetable> cartVegetable = cart.getCartVegetables();

        double totalPrice = 0.0;
        for (int i = 0; i < cartVegetable.size(); i++) {
            if (Objects.equals(cartVegetable.get(i).getVegetable().getVegetableId(), vegetable.getVegetableId()))
                throw new CartException("Vegetable already exists in the cart");
            totalPrice += cartVegetable.get(i).getQuantityForCart() * cartVegetable.get(i).getVegetable().getPrice();
        }
        CartVegetable middle = new CartVegetable();

        CartVegetableId myId = new CartVegetableId(cart.getCartId(), vegetable.getVegetableId());
        middle.setQuantityForCart(1);
        middle.setId(myId);
        middle.setCart(cart);

        middle.setVegetable(vegetable);

        cartVegetable.add(middle);

        totalPrice += vegetable.getPrice();

        cart.setTotalPrice(totalPrice);
        System.out.println("Cart => " + middle.getId() + " " + middle.getCart() + " " + middle.getQuantityForCart());

        cartRepository.save(cart);
        return cart;
    }

    private double calculateTotalPrice(Cart cart) {
        double totalPrice = 0.0;
        if (cart == null) throw new CartException("Cart is null");
        if (cart.getCartVegetables() == null || cart.getCartVegetables().size() == 0) return 0;

        List<CartVegetable> cartVegetable = cart.getCartVegetables();
        System.out.println("Cart list before printing => "+ cartVegetable);
        for (int i = 0; i < cartVegetable.size(); i++) {
            totalPrice += cartVegetable.get(i).getQuantityForCart() * cartVegetable.get(i).getVegetable().getPrice();
        }
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
        return totalPrice;
    }


    @Override
    public void increaseQuantity(Integer customerId, Vegetables vegetables, int quantity) throws CartException {
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerException("Customer not found"));
        Vegetables existingVegetable = vegetableRepository.findById(vegetables.getVegetableId()).orElseThrow(() -> new VegetablesException("Vegetable Not found"));

        List<CartVegetable> cartVegetable = existingCustomer.getCart().getCartVegetables();
        for (int i = 0; i < cartVegetable.size(); i++) {
            if (cartVegetable.get(i).getVegetable() == existingVegetable) {
                cartVegetable.get(i).setQuantityForCart(quantity);
                cartVegetableRepository.save(cartVegetable.get(i));

                existingCustomer.getCart().setTotalPrice(calculateTotalPrice(existingCustomer.getCart()));
                cartRepository.save(existingCustomer.getCart());
            }
        }


    }

    @Override
    public void decreseQuantity(Integer vegetable, int quantity) throws CartException {

        Optional<Vegetables> vegetableOpt = vegetableRepository.findById(vegetable);
        if (vegetableOpt.isPresent()) {

            Vegetables vegetableEntity = vegetableOpt.get();
            int existingQuantity = vegetableEntity.getQuantity();
            int newQuantity = existingQuantity - quantity;
            vegetableEntity.setQuantity(newQuantity);
            vegetableRepository.save(vegetableEntity);

            System.out.println("Successfully decreased " + quantity);
        }
        throw new CartException("Vegetable Not found");
    }

    @Override
    public Cart removeVegetableFromCart(Integer customerId, Vegetables vegetables) throws CartException {

        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerException("Customer Not found"));

        Vegetables existingVegetable = vegetableRepository.findById(vegetables.getVegetableId()).orElseThrow(()->new VegetablesException("Vegetable not found"));


        Cart existingCart = existingCustomer.getCart();

        List<CartVegetable> cartVegetableList = existingCart.getCartVegetables();
        for(int i=0;i<cartVegetableList.size();i++){
            System.out.println("Vegetable Id: in middle table: "+ cartVegetableList.get(i).getVegetable().getVegetableId());

            if(cartVegetableList.get(i).getVegetable() == existingVegetable){
                CartVegetable temp = cartVegetableList.get(i);
                cartVegetableList.remove(temp);
                System.out.println("Check if removed..");
                cartVegetableRepository.delete(temp);
                calculateTotalPrice(existingCart);
                return existingCart;
            }

        }
        throw new CartException("Vegetable not found in your cart: ");
    }

    @Override
    public String removeAllVegetablesFromCart(Integer cartId) throws CartException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartException("Cart not found"));

//        cart.getVegetablesList().clear();

        cartRepository.save(cart);

        System.out.println("Successfully removed all vegetables from the cart.");
        return "Successfully removed all vegetables from the cart.";
    }

    @Override
    public List<Vegetables> viewVegetableList(Integer customerId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerException("Customer not found"));

        System.out.println("Customer name " + customer.getName());
//        return customer.getCart().getVegetablesList();
        return null;
    }

    @Override
    public List<Vegetables> getAllVegetablesFromCart(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerException("Customer Not found"));
        List<Vegetables> vegetablesList = new ArrayList<>();

        List<CartVegetable> middle = customer.getCart().getCartVegetables();
        for(int i=0;i<middle.size();i++){
            int quantity = middle.get(i).getQuantityForCart();
            Vegetables temp = middle.get(i).getVegetable();
            temp.setQuantity(quantity);
           vegetablesList.add(temp);
        }
        return vegetablesList;
    }


}
