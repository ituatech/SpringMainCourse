package com.it_uatech.dao;

import com.it_uatech.domain.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoSimple implements PersonDao {

    @Value("${user.age}")
    private int age;

    public Person findByName(String name) {
        return new Person(name, age);
    }
}
