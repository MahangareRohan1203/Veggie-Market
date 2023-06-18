package com.vegetablemart.controller;


import com.vegetablemart.entities.BillingDetails;
import com.vegetablemart.entitiesDto.BillingDetailsDto;
import com.vegetablemart.service.BillingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class BillingController {

    @Autowired
    private BillingService billingService;


    @PostMapping  ("/bill-detail/{cid}/{oid}")
    public ResponseEntity<BillingDetails> AddNewBillDetailsHandler(
            @PathVariable("cid") Integer cid,
            @PathVariable("oid") Integer oid,
            @Valid @RequestBody BillingDetails billingDetails){

        System.out.println("billing details"+billingDetails.getTotalAmount());
        BillingDetails res=billingService.AddBill(cid, oid, billingDetails);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping ("/update-bill/{billid}/{oid}")
    public ResponseEntity<BillingDetails> updateBillingDetailsHandler(
            @PathVariable("billid") Integer billid,
            @PathVariable("oid") Integer oid,
             @Valid BillingDetailsDto billingDetailsDto){

        BillingDetails res=billingService.updateBillingDetails(billid, oid, billingDetailsDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @GetMapping("/bill-details/{bid}")
    public ResponseEntity<BillingDetails> getBillDetailsHandler(
            @PathVariable("bid") Integer bid){

        BillingDetails response=billingService.viewBills(bid);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
