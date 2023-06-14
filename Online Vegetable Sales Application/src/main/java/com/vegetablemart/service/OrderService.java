package com.vegetablemart.service;

import com.vegetablemart.entities.Orders;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    public Orders addOrder(Integer customerId, Orders orders);

    public Orders viewOrder(Integer orderId);

    public Orders updateOrder(Integer orderId, Orders orders);

    public List<Orders> viewAllOrdersByCustomerId(Integer customerId);

    public List<Orders> viewAllOrdersByDate(LocalDate date);

    public List<Orders> viewAllOrderList();

    public Orders cancelOrder(Integer orderId);
}
