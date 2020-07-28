package com.it_uatech.repositories.jpa;


import com.it_uatech.models.jpa.common.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CourseRepositoryJpaImpl implements CourseRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Course save(Course course) {
        em.persist(course);
        return course;
    }
}
