package com.it_uatech.repository;

import com.it_uatech.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person,Integer> {

    List<Person> findAll();

    Person findByName(String name);

}
