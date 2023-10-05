package com.peopleapi.peopleapi.model;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
@Entity
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank (message = "Name cannot be null")
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")  @NotBlank (message = "Birthday Date cannot be null")
    private Date birthdayDate;
    @OneToMany(fetch = FetchType.LAZY, cascade =  {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Address> address;
}

