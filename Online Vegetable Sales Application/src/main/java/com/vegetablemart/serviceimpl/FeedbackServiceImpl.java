package com.vegetablemart.serviceimpl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.vegetablemart.entities.Customer;
import com.vegetablemart.entities.Feedback;

import com.vegetablemart.entities.Vegetables;
import com.vegetablemart.exceptions.CustomerException;
import com.vegetablemart.exceptions.FeedBackException;

import com.vegetablemart.exceptions.VegetablesException;
import com.vegetablemart.repository.CustomerRepository;
import com.vegetablemart.repository.FeedbackRepository;

import com.vegetablemart.repository.VegetablesRepository;
import com.vegetablemart.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository fdao;

	@Autowired
	private CustomerRepository  custDao;

	@Autowired
	private VegetablesRepository vegDao;
	
	

	@Override
	public Feedback addFeedBack(Feedback feedback, Integer vegetableId, Integer customerId) throws VegetablesException, CustomerException {
	    // Check if the customer exists
	    Optional<Customer> customerOptional = custDao.findById(customerId);
	    if (customerOptional.isEmpty()) {
	        throw new CustomerException("Customer is not present with Id: " + customerId);
	    }
	    
	    // Check if the vegetable exists
	    Optional<Vegetables> vegetableOptional = vegDao.findById(vegetableId);
	    if (vegetableOptional.isEmpty()) {
	        throw new VegetablesException("Vegetable is not present with Id: " + vegetableId);
	    }
	    
	    // Set the customer and vegetable for the feedback
	    feedback.setCustomer(customerOptional.get());
	    feedback.setVegetableId(vegetableId);
	    
	    // Set the feedback date and time to the current date and time
	    feedback.setFeedbackDateTime(LocalDateTime.now());
	    
	    // Save the feedback
	    Feedback savedFeedback = fdao.save(feedback);
	    
	    return savedFeedback;
	}


	@Override
	public Feedback updateFeedBack(Feedback feedback) throws FeedBackException {
	    Optional<Feedback> optional = fdao.findById(feedback.getFeedbackId());

	    if (optional.isPresent()) {
	        Feedback existingFeedback = optional.get();
	        Optional<Vegetables> vegetablesOptional = vegDao.findById(feedback.getVegetableId());

	        if (!vegetablesOptional.isPresent()) {
	            throw new FeedBackException("Invalid vegetable details!");
	        }

	        Vegetables vegetables = vegetablesOptional.get();
	        existingFeedback.setVegetableId(vegetables.getVegetableId());
	        existingFeedback.setRating(feedback.getRating());
	        existingFeedback.setComments(feedback.getComments());
	        existingFeedback.setFeedbackDateTime(LocalDateTime.now());

	        return fdao.save(existingFeedback);
	    } else {
	        throw new FeedBackException("No feedback found!");
	    }
	}


	@Override
	public Feedback viewFeedback(Integer id) throws FeedBackException {
		Optional<Feedback> fedOptional = fdao.findById(id);
		if (fedOptional.isPresent()) {
			return fedOptional.get();
		}
		throw new FeedBackException("No feedback found!");
	}

	@Override
	public List<Feedback> viewFeedbackAll() throws FeedBackException {
		Optional<List<Feedback>> fedOptional = Optional.of(fdao.findAll());
		if (fedOptional.isPresent()) {
			return fedOptional.get();
		}
		throw new FeedBackException("No feedbacks found!");
	}

	@Override
	public Feedback deleteFeedBack(Integer feedbackId) throws FeedBackException {
	
		Optional<Feedback> fedOptional = fdao.findById(feedbackId);
		
		if (fedOptional.isPresent()) {
			Feedback existingFeedback = fedOptional.get();
			fdao.delete(existingFeedback);
			return existingFeedback;
		}
		throw new FeedBackException("No feedback found!");
	}
}
