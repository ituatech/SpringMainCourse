package com.it_uatech.dao;

import com.it_uatech.logging.Loggable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.it_uatech.domain.Person;

@Repository
public class PersonDaoSimple implements PersonDao {

    @Value("${person.age}")
    private int age;

    @Loggable
    public Person findByName(String name) {
        return new Person(name, age);
    }
}
