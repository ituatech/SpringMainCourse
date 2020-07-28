package com.it_uatech.repositories.jdbc;

import com.it_uatech.models.jdbc.MyStudent;

import java.util.List;

public interface MyStudentRepositoryJdbc {
    List<MyStudent> findAllWithAllInfo();
}
