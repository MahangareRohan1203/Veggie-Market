package com.vegetablemart.service;

import com.vegetablemart.entities.Feedback;
import com.vegetablemart.exceptions.CustomerException;
import com.vegetablemart.exceptions.FeedBackException;
import com.vegetablemart.exceptions.VegetablesException;

import java.util.List;


public interface FeedbackService {


    public Feedback addFeedBack(Feedback feedBack, Integer vegetableId, Integer customerId) throws VegetablesException, CustomerException;

    public Feedback updateFeedBack(Feedback feedback) throws FeedBackException;

    public Feedback viewFeedback(Integer id) throws FeedBackException;

    public List<Feedback> viewFeedbackAll() throws FeedBackException;


}
