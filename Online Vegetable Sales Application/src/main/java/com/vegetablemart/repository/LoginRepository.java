package com.vegetablemart.repository;

import com.vegetablemart.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {

    public User findByUserName(String email);
}
