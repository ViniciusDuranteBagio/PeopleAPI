package com.peopleapi.peopleapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.peopleapi.peopleapi.exception.people.PeopleNotFoundException;
import com.peopleapi.peopleapi.model.People;
import com.peopleapi.peopleapi.repository.PeopleRepository;

import jakarta.validation.Valid;


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

    @GetMapping("/{id}")
    public People show(@PathVariable Long id) throws PeopleNotFoundException
    {
       return peopleRepository.findById(id).orElseThrow(() -> new PeopleNotFoundException(id));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public People savePeople(@RequestBody @Valid People people)
    {
        return peopleRepository.save(people);
    }


    @PutMapping("/update/{id}")
    public People update(@PathVariable Long id, @RequestBody People people) throws PeopleNotFoundException {
        People peopleDatabase = peopleRepository.findById(id).orElseThrow(() -> new PeopleNotFoundException(id));
        peopleDatabase.setName(people.getName());
        peopleDatabase.setBirthdayDate(people.getBirthdayDate());
        return peopleRepository.save(peopleDatabase);
    }

}
