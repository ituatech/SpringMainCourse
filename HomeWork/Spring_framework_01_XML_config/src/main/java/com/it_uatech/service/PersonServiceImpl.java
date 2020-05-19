package com.it_uatech.service;

import com.it_uatech.dao.PersonDao;
import com.it_uatech.domain.Person;

public class PersonServiceImpl implements PersonService {

    private PersonDao dao;


    public void setDao(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
