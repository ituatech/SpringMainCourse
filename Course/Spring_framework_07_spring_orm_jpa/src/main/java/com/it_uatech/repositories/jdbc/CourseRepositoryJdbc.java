package com.it_uatech.repositories.jdbc;

import com.it_uatech.models.jdbc.Course;

import java.util.List;

public interface CourseRepositoryJdbc {
    List<Course> findAllUsed();
}
