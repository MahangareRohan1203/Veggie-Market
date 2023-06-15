package com.vegetablemart.service;

import java.util.List;

import com.vegetablemart.entities.Feedback;
import com.vegetablemart.exceptions.CustomerException;
import com.vegetablemart.exceptions.FeedBackException;
import com.vegetablemart.exceptions.VegetablesException;
import com.vegetablemart.exceptions.UserException;



public interface FeedbackService {


   public Feedback addFeedBack(Feedback feedBack,Integer vegetableId,Integer customerId) throws VegetablesException, CustomerException;
	
	public Feedback updateFeedBack(Feedback feedback) throws FeedBackException;
	
	public Feedback deleteFeedBack(Integer feedbackId)throws FeedBackException;

	public Feedback viewFeedback(Integer id) throws FeedBackException;

	public List<Feedback> viewFeedbackAll() throws FeedBackException;
	
	
}
