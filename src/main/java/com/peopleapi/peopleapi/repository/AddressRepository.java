package com.peopleapi.peopleapi.repository;

import org.springframework.stereotype.Repository;
import com.peopleapi.peopleapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
