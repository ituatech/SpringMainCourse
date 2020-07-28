package com.it_uatech.repostory;

import com.it_uatech.domain.Person;

import java.util.List;

public interface PersonRepository {

    void insert(Person p);

    Person getById(int id);

    Person getFirst();

    List<Person> getAll();

    Person getByName(String name);
}
