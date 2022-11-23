package com.testspring.springtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testspring.springtest.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
    
}
