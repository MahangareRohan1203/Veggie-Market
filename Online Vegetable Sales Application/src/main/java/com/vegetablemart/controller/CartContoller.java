package com.vegetablemart.controller;

import com.vegetablemart.entities.Cart;
import com.vegetablemart.entities.Vegetables;
import com.vegetablemart.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/veggieMarket")
public class CartContoller {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart/{customerId}")
    public ResponseEntity<Cart> addToCartHandler(
            @PathVariable Integer customerId, @RequestBody Vegetables vegetables) {
        Cart addedToCartCart = cartService.addToCart(customerId, vegetables);
        return new ResponseEntity<>(addedToCartCart, HttpStatus.CREATED);
    }

    @PutMapping("/cart-veg-qty-inc/{customerId}")
    public ResponseEntity<String> increaseQuantityHandler(
           @PathVariable Integer customerId,@RequestBody Vegetables vegetables,
            @RequestParam Integer quantity) {

        cartService.increaseQuantity(customerId, vegetables, quantity);
        String message = "succesfully Increases";
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @PutMapping("/cart-veg-qty-dec/{customerId}")
    public ResponseEntity<String> descreseQuantityHandler(
            @PathVariable Integer customerId,@RequestBody Vegetables vegetables,
            @RequestParam Integer quantity) {
        cartService.increaseQuantity(customerId, vegetables, quantity);
        String message = "succesfully decreases with some changes";
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @DeleteMapping("/removeItem/{customerId}")
    public ResponseEntity<Cart> removeFromCartHandler(
            @PathVariable Integer customerId,
            @RequestBody Vegetables vegetables) {

        Cart removedafter = cartService.removeVegetableFromCart(customerId, vegetables);
        return new ResponseEntity<>(removedafter, HttpStatus.OK);
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<String> removeAllVegetablesFromCartHander(@PathVariable("cartId") Integer cartId) {
        String message = cartService.removeAllVegetablesFromCart(cartId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/vegetables/{cartId}")
    public ResponseEntity<List<Vegetables>> viewVegetableListHandler(
            @PathVariable("cartId") Integer cartId) {
        List<Vegetables> vegetablesList = cartService.viewVegetableList(cartId);
        return new ResponseEntity<>(vegetablesList, HttpStatus.OK);
    }

    @GetMapping("/vegtables/{customerId}")
    public ResponseEntity<List<Vegetables>> getVegetablesFromCart(@PathVariable Integer customerId){
        List<Vegetables> vegetablesList = cartService.getAllVegetablesFromCart(customerId);
        return new ResponseEntity<>(vegetablesList, HttpStatus.OK);

    }
}
