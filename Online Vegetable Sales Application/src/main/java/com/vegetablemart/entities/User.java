package com.vegetablemart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.Temporal;

@Entity
@Getter
@Setter
public class User {
    @Id
    private String userName;

    @OneToOne
    @JsonIgnore
    private Customer customer;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String role;

    private Integer userId;
}
