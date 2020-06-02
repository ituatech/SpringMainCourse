package com.it_uatech.service;

import org.springframework.stereotype.Service;
import com.it_uatech.dao.PersonDao;
import com.it_uatech.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
