package com.peopleapi.peopleapi.exception.people;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PeopleNotFoundException extends PeopleException {
    public PeopleNotFoundException(Long id) {
        super("People not found with ID " + id + "!");
    }
}
