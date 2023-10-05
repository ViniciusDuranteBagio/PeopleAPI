package com.peopleapi.peopleapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    @NotBlank (message = "Zip Code cannot be null")
    private Integer zipCode;
    private String publicPlace;
    @NotBlank (message = "City cannot be null")
    private String city;
    private Boolean main;
}
