package com.vegetablemart.serviceimpl;

import com.vegetablemart.entities.Customer;
import com.vegetablemart.entities.Orders;
import com.vegetablemart.enums.OrderStatus;
import com.vegetablemart.exceptions.AmountException;
import com.vegetablemart.exceptions.CustomerException;
import com.vegetablemart.exceptions.OrdersException;
import com.vegetablemart.repository.CustomerRepository;
import com.vegetablemart.repository.OrdersRepository;
import com.vegetablemart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Orders addOrder(Integer customerId, Orders orders) {
        System.out.println("Customer Id: " + customerId + " orders: " + orders.getVegetableList());
        orders.setOrderId(null);
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerException("Customer Not found"));
        //TODO: Validate Customer and set relations
        Integer totalAmount = 0;
        if (orders.getVegetableList() != null || orders.getVegetableList().size() != 0) {
            for (int i = 0; i < orders.getVegetableList().size(); i++) {
                totalAmount += orders.getVegetableList().get(i).getVegetableDtoPrice()  * orders.getVegetableList().get(i).getVegetableQuantity();
            }

            if (!totalAmount.equals(orders.getTotalAmount()))
                throw new AmountException("Total Amount should be " + totalAmount);
        }
        else throw new OrdersException("Vegetable List cannot be empty.");
        orders.setStatus(OrderStatus.PENDING);
        orders.setTotalAmount(totalAmount);
        orders.setCustomer(existingCustomer);
        orders = ordersRepository.save(orders);
        existingCustomer.getOrdersList().add(orders);
        customerRepository.save(existingCustomer);

        return orders;

    }

    @Override
    public Orders viewOrder(Integer orderId) {
        return ordersRepository.findById(orderId).orElseThrow(() -> new OrdersException("Order not Found."));
    }

    @Override
    public Orders updateOrder(Integer orderId, Orders orders) {
        Orders existingOrder = ordersRepository.findById(orderId).orElseThrow(() -> new OrdersException("Order not Found."));

        if (orders.getVegetableList() != null) {
            Integer totalAmount = 0;
            for (int i = 0; i < orders.getVegetableList().size(); i++) {
                totalAmount += orders.getVegetableList().get(i).getVegetableDtoPrice() * orders.getVegetableList().get(i).getVegetableQuantity();
            }

            if (!totalAmount.equals(orders.getTotalAmount()))
                throw new AmountException("Total Amount should be " + totalAmount);
            existingOrder.setVegetableList(orders.getVegetableList());
            existingOrder.setTotalAmount(orders.getTotalAmount());
        }

        if (orders.getStatus() != null) existingOrder.setStatus(orders.getStatus());
        ordersRepository.save(existingOrder);
        return existingOrder;
    }

    @Override
    public List<Orders> viewAllOrdersByCustomerId(Integer customerId) {
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(()->new CustomerException("Customer Not found"));
        // TODO: Validate the customer
        List<Orders> ordersList = existingCustomer.getOrdersList();
        if(ordersList == null || ordersList.size() == 0) throw new OrdersException("Order List is Empty.");

        return ordersList;
    }

    @Override
    public List<Orders> viewAllOrdersByDate(LocalDate date) {

        LocalDateTime start = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(date, LocalTime.MAX);
        List<Orders> ordersList = ordersRepository.getOrdersByDateRange(start, end);
        if (ordersList == null || ordersList.size() == 0)
            throw new OrdersException("Order List is Empty for Date: " + date);
        return ordersList;
    }

    @Override
    public List<Orders> viewAllOrderList() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders cancelOrder(Integer orderId) {
        Orders existingOrder = ordersRepository.findById(orderId).orElseThrow(() -> new OrdersException("Order Not found."));
        if (existingOrder.getStatus() != OrderStatus.DELIVERED) {
            existingOrder.setStatus(OrderStatus.CANCELLED);
            ordersRepository.save(existingOrder);
        } else throw new OrdersException("Order is delivered. It cannot be cancelled now.");

        return existingOrder;
    }
}
