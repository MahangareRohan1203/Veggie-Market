package com.vegetablemart.controller;

import com.vegetablemart.entities.Cart;
import com.vegetablemart.entities.Vegetables;
import com.vegetablemart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartContoller {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart-create/{cid}")
    public ResponseEntity<Cart> generateCartForCustomerHandler(@PathVariable("cid") Integer cid) {
        Cart genertedCart = cartService.generateCartForCustomer(cid);
        return new ResponseEntity<>(genertedCart, HttpStatus.CREATED);
    }

    @PostMapping("/cart/{cartId}/{vegetableId}/{vegetable_qty}")
    public ResponseEntity<Cart> addToCartHandler(
            @PathVariable("cartId") Integer cartId,
            @PathVariable("vegetableId") Integer vegetableId,
            @PathVariable("vegetable_qty") Integer vegetable_qty) {
        Cart addedToCartCart = cartService.addToCart(cartId, vegetableId, vegetable_qty);
        return new ResponseEntity<>(addedToCartCart, HttpStatus.CREATED);

    }

    @PutMapping("/cart-veg-qty-inc/{vegetableId}/{vegetable_qty}")
    public ResponseEntity<String> increaseQuantityHandler(
            @PathVariable("vegetableId") Integer vegetableId,
            @PathVariable("vegetable_qty") Integer vegetable_qty) {
          cartService.increaseQuantity(vegetableId,vegetable_qty);
        String message="succesfully Increases";
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @PutMapping("/cart-veg-qty-dec/{vegetableId}/{vegetable_qty}")
    public ResponseEntity<String> descreseQuantityHandler(
            @PathVariable("vegetableId") Integer vegetableId,
            @PathVariable("vegetable_qty") Integer vegetable_qty) {
        cartService.decreseQuantity(vegetableId,vegetable_qty);
        String message="succesfully decreases";
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @DeleteMapping("/removeItem/{vegetableId}/{cartId}")
    public ResponseEntity<Cart> removeFromCartHander(
            @PathVariable("vegetableId") Integer vegetableId,
            @PathVariable("cartId") Integer cartId ) {

         Cart removedafter=  cartService.removeVegetableFromCart(vegetableId,cartId);
        return new ResponseEntity<>(removedafter,HttpStatus.OK);
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<String> removeAllVegetablesFromCartHander(@PathVariable("cartId") Integer cartId) {
        String message=cartService.removeAllVegetablesFromCart(cartId);
        return  new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/vegetables/{cartId}")
    public ResponseEntity<List<Vegetables>> viewVegetableListHandler(
            @PathVariable("cartId") Integer cartId){
       List<Vegetables> vegetablesList= cartService.viewVegetableList(cartId);
           return new ResponseEntity<>(vegetablesList,HttpStatus.OK);
    }
}
