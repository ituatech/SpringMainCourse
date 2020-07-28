package com.it_uatech.repositories.jpa;

import com.it_uatech.models.jpa.MyStudentV2;

import java.util.List;

public interface MyStudentV2RepositoryJpa {
    List<MyStudentV2> findAllWithEntityGraph();
    List<MyStudentV2> findAllWithJoinFetch();
}
