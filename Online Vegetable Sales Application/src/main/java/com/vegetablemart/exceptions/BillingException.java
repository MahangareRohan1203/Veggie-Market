package com.vegetablemart.exceptions;

public class BillingException extends RuntimeException{
    public BillingException(){

    }
    public BillingException(String message){
         super(message);
    }
}
