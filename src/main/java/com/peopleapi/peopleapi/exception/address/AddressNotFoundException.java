package com.peopleapi.peopleapi.exception.address;

public class AddressNotFoundException extends AddressException {
    public AddressNotFoundException(Long id) {
        super("Address not found with ID " + id);
    }  
}
