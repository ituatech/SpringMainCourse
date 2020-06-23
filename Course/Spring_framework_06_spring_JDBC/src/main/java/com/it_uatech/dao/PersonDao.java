package com.it_uatech.dao;

import com.it_uatech.domain.Person;

import java.util.List;

public interface PersonDao {

    int count();

    void insert(Person person);

    Person getById(int id);

    void deleteById(int id);

    List<Person> getAll();

}
