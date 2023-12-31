package com.vegetablemart.serviceimpl;

import com.vegetablemart.entities.BillingDetails;
import com.vegetablemart.entities.Customer;
import com.vegetablemart.entities.*;
import com.vegetablemart.entitiesDto.BillingDetailsDto;
import com.vegetablemart.enums.OrderStatus;
import com.vegetablemart.enums.TransactionStatus;
import com.vegetablemart.exceptions.BillingException;
import com.vegetablemart.exceptions.OrdersException;
import com.vegetablemart.repository.CustomerRepository;
import com.vegetablemart.repository.*;
import com.vegetablemart.service.BillingService;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
   private  BillingRepository billingRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public BillingDetails AddBill(Integer customerId, Integer orderId, BillingDetails billingDetails) throws BillingException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BillingException("Invalid CustomerId"));
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new BillingException("Invalid OrderId"));

        Address address = addressRepository.save(billingDetails.getAddress());
        address.setCustomer(customer);
        customer.getCustomerAddress().add(address);
        if(order.getBillingDetails() != null && order.getBillingDetails().getTransactionStatus() == TransactionStatus.SUCCESS) throw new TransactionException("Transaction Already done.");
        System.out.println(order.getTotalAmount()+"   => "+ billingDetails.getTotalAmount());
        if(order.getTotalAmount().doubleValue() != billingDetails.getTotalAmount())  throw new OrdersException("Provided amount is not matched");


        billingDetails = billingRepository.save(billingDetails);
        billingDetails.setTransactionStatus(TransactionStatus.SUCCESS);
        billingDetails.setTransactionDateTime(LocalDateTime.now());
        order.setStatus(OrderStatus.PROCESSING);
        order.setBillingDetails(billingDetails);
        billingDetails.setOrders(order);


        customer.getOrdersList().add(order);
        ordersRepository.save(order);
        billingDetails = billingRepository.save(billingDetails);

        return billingDetails;
    }

    public BillingDetails updateBillingDetails(Integer orderId, Integer billingId, BillingDetailsDto billingDetailsDto) throws BillingException {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new BillingException("Invalid OrderId"));

        BillingDetails billingDetails = billingRepository.findById(billingId)
                .orElseThrow(() -> new BillingException("Invalid BillingId"));

        billingDetails.setTotalAmount(billingDetailsDto.getTotalAmount());
        billingDetails.setTransactionMode(billingDetailsDto.getTransactionMode());
        billingDetails.setTransactionStatus(billingDetailsDto.getTransactionStatus());
        billingDetails.setAddress(billingDetailsDto.getAddress());

        order.setBillingDetails(billingDetails);
        ordersRepository.save(order);

        return billingDetails;
    }

    @Override
    public BillingDetails viewBills(Integer billingId) throws BillingException {
        return billingRepository.findById(billingId)
                .orElseThrow(() -> new BillingException("Invalid BillingId"));
    }

}
