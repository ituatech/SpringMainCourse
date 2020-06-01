package com.it_uatech.dao;

import com.it_uatech.domain.Person;

public class PersonDaoSimple implements PersonDao {

    private final int age;

    public PersonDaoSimple(int age) {
        this.age = age;
    }

    public Person findByName(String name) {
        return new Person(name, age);
    }
}
