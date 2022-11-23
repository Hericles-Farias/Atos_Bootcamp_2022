package com.br.batteryswapping.batteryswappingstation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.batteryswapping.batteryswappingstation.model.Person;
import com.br.batteryswapping.batteryswappingstation.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping()
    public String savePerson(@RequestBody Person person){
        personRepository.save(person);
        return "done";
    }

    @GetMapping()
    public List<Person> searchPeople(){
        return personRepository.findAll();
    }
    
}
