package com.example.userauthenticationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class BaseModel {
    // auto generate id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

}
