package com.it_uatech.repositories.jpa;

import com.it_uatech.models.jpa.MyStudent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MyStudentRepositoryJpaImpl implements MyStudentRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<MyStudent> findById(long id) {
        return Optional.ofNullable(em.find(MyStudent.class, id));
    }

    @Override
    public List<MyStudent> findAll() {
        return em.createQuery("select ms from MyStudent ms", MyStudent.class).getResultList();
    }

    @Override
    public MyStudent save(MyStudent student) {
        if (student.getId() <= 0) {
            em.persist(student);
            return student;
        } else {
            return em.merge(student);
        }
    }
}
