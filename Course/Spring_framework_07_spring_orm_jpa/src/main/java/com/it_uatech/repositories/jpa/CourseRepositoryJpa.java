package com.it_uatech.repositories.jpa;


import com.it_uatech.models.jpa.common.Course;

public interface CourseRepositoryJpa {
    Course save(Course course);
}
