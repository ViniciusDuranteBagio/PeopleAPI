package com.peopleapi.peopleapi.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peopleapi.peopleapi.exception.address.AddressNotFoundException;
import com.peopleapi.peopleapi.exception.people.PeopleNotFoundException;
import com.peopleapi.peopleapi.model.Address;
import com.peopleapi.peopleapi.model.People;
import com.peopleapi.peopleapi.repository.AddressRepository;
import com.peopleapi.peopleapi.repository.PeopleRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    
    @Autowired
    private PeopleRepository peopleRepository;

   @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/{peopleId}")
    public List<Address> showAddress(@PathVariable Long peopleId) throws PeopleNotFoundException
    {
        People people = peopleRepository.findById(peopleId).orElseThrow(() -> new PeopleNotFoundException(peopleId));
        return people.getAddress();
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable Long id, @RequestBody @Valid Address address) throws AddressNotFoundException {
        Address addressDB = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
        addressDB.setNumber(address.getNumber());
        addressDB.setCity(address.getCity());
        addressDB.setZipCode(address.getZipCode());
        addressDB.setPublicPlace(address.getPublicPlace());
        addressDB.setMain(address.getMain());
        return addressRepository.save(addressDB);
    }

    @PostMapping("/{peopleId}")
    public List<Address> insertAddress(@PathVariable Long peopleId, @RequestBody @Valid Address address) throws PeopleNotFoundException {
        People people = peopleRepository.findById(peopleId).orElseThrow(() -> new PeopleNotFoundException(peopleId));
        List<Address> addressToUpdate = people.getAddress();
        addressToUpdate.add(address);
        people.setAddress(addressToUpdate);
        peopleRepository.save(people);
        return addressToUpdate;
    }


   



}
