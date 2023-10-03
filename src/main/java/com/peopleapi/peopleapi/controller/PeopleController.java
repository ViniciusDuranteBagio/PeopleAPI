package com.peopleapi.peopleapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.peopleapi.peopleapi.model.People;



@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {
    
    @GetMapping
    public List<People> listAll()
    {
      return null;
    }

}
