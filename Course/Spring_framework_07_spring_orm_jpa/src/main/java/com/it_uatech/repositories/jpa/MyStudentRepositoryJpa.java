package com.it_uatech.repositories.jpa;

import com.it_uatech.models.jpa.MyStudent;

import java.util.List;
import java.util.Optional;

public interface MyStudentRepositoryJpa {
    Optional<MyStudent> findById(long id);
    List<MyStudent> findAll();
    MyStudent save(MyStudent student);

}
