package com.it_uatech.dao;

import com.it_uatech.domain.Person;

public interface PersonDao {

    Person findByName(String name);
}
