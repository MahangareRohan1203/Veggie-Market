package com.vegetablemart.controller;

import com.vegetablemart.entities.Feedback;
import com.vegetablemart.exceptions.FeedBackException;
import com.vegetablemart.service.FeedbackService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vegiee")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/user/feedback/add/{vegetableId}/{customerId}")
    public ResponseEntity<Feedback> addFeedback(@Valid @RequestBody Feedback feedback,
                                                @PathVariable("vegetableId") Integer vegetableId,
                                                @PathVariable("customerId") Integer customerId) throws FeedBackException {
        // Add feedback using the provided service method
        Feedback feedback2 = feedbackService.addFeedBack(feedback, vegetableId, customerId);

        return new ResponseEntity<Feedback>(feedback2, HttpStatus.CREATED);
    }

    @PutMapping("/user/feedback/update")
    public ResponseEntity<Feedback> updateFeedback(@Valid @RequestBody Feedback feedback) throws FeedBackException {
        // Update the feedback using the provided service method
        Feedback feedback2 = feedbackService.updateFeedBack(feedback);

        return new ResponseEntity<Feedback>(feedback2, HttpStatus.ACCEPTED);
    }

    @GetMapping("/feedback/{feedbackId}")
    public ResponseEntity<Feedback> viewFeedback(@PathVariable("feedbackId") Integer feedbackId) throws FeedBackException {
        // Retrieve a specific feedback using the provided service method
        Feedback feedback2 = feedbackService.viewFeedback(feedbackId);

        return new ResponseEntity<Feedback>(feedback2, HttpStatus.ACCEPTED);
    }

    @GetMapping("/feedback/all")
    public ResponseEntity<List<Feedback>> viewFeedbackAll() throws FeedBackException {
        // Retrieve all feedbacks using the provided service method
        List<Feedback> feedback2 = feedbackService.viewFeedbackAll();

        return new ResponseEntity<List<Feedback>>(feedback2, HttpStatus.ACCEPTED);
    }
}
