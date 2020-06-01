package com.it_uatech.service;

import com.it_uatech.domain.Person;
import com.it_uatech.dao.PersonDao;

public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
