package com.peopleapi.peopleapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peopleapi.peopleapi.model.People;
import com.peopleapi.peopleapi.repository.PeopleRepository;


@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {
    
    @Autowired
    private PeopleRepository peopleRepository;


    @GetMapping
    public List<People> listAll()
    {
       return peopleRepository.findAll();
    }

    @PostMapping
    public People savePeople(@RequestBody People people)
    {
        return peopleRepository.save(people);
    }


}
