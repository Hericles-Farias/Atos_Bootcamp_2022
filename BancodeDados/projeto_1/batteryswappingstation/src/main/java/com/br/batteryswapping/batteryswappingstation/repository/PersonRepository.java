package com.br.batteryswapping.batteryswappingstation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.batteryswapping.batteryswappingstation.model.Person;
public interface PersonRepository extends JpaRepository<Person,Long>{
    
}
