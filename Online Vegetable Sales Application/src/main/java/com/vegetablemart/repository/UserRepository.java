package com.vegetablemart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vegetablemart.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {

}
