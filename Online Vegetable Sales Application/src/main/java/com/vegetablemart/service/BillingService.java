package com.vegetablemart.service;

import com.vegetablemart.entities.BillingDetails;
import com.vegetablemart.entitiesDto.BillingDetailsDto;
import com.vegetablemart.exceptions.BillingException;

public interface BillingService {

    public BillingDetails AddBill(Integer customerId,Integer orderId,BillingDetails billingDetails) throws BillingException;


    public BillingDetails updateBillingDetails(Integer orderId, Integer BillingId,BillingDetailsDto billingDetailsDto)throws BillingException;

    public BillingDetails viewBills(Integer BillingId)throws BillingException;

}
