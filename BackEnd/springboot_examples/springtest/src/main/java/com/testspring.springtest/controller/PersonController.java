package com.testspring.springtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.testspring.springtest.model.Person;
import com.testspring.springtest.repository.PersonRepository;


@RestController("/person")
public class PersonController {
    
    @Autowired
    PersonRepository pessoaRepository;

    @PostMapping()
    public Person salvarPessoa(@RequestBody Person person){
        return pessoaRepository.save(person);
    }
    @GetMapping()
    public List<Person> buscarTodas(){
        return pessoaRepository.findAll();
    }
    
}
