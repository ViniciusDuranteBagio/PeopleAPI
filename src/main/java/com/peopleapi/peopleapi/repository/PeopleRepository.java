package com.peopleapi.peopleapi.repository;

import org.springframework.stereotype.Repository;
import com.peopleapi.peopleapi.model.People;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long>{
    
}
