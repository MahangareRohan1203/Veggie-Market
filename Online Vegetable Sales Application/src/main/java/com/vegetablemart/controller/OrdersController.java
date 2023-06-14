package com.vegetablemart.controller;

import com.vegetablemart.entities.Orders;
import com.vegetablemart.serviceimpl.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
public class OrdersController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/orders/{customerId}")
    public ResponseEntity<Orders> addNewOrderHandler(@PathVariable Integer customerId, @RequestBody @Valid Orders orders) {
        orders = orderService.addOrder(customerId, orders);
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Orders> getOrderByIdHandler(@PathVariable Integer orderId) {
        Orders existingOrder = orderService.viewOrder(orderId);
        return new ResponseEntity<>(existingOrder, HttpStatus.OK);
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Orders> updateOrderHandler(@RequestBody Orders orders, @PathVariable Integer orderId) {
        orders = orderService.updateOrder(orderId, orders);
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @GetMapping("/orders/c/{customerId}")
    public ResponseEntity<List<Orders>> getAllOrdersByCustomerId(@PathVariable Integer customerId) {
        List<Orders> ordersList = orderService.viewAllOrdersByCustomerId(customerId);
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @GetMapping("/orders/date/{date}")
    public ResponseEntity<List<Orders>> getAllOrdersByDateHandler(@PathVariable LocalDate date) {
        List<Orders> ordersList = orderService.viewAllOrdersByDate(date);
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrdersHandler() {
        List<Orders> ordersList = orderService.viewAllOrderList();
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @PatchMapping("/orders/cancel/{orderId}")
    public ResponseEntity<Orders> canExistingOrderHandler(@PathVariable Integer orderId) {
        Orders cancelledOrder = orderService.cancelOrder(orderId);
        return new ResponseEntity<>(cancelledOrder, HttpStatus.ACCEPTED);
    }

}
