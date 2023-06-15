package com.vegetablemart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vegetablemart.entities.Feedback;


public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
