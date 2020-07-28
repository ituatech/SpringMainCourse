package com.it_uatech.repostory;

import com.it_uatech.domain.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class PersonRepositoryJpa implements PersonRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Person p) {
        em.persist(p);
    }

    @Override
    public Person getById(int id) {
        return em.find(Person.class, id);
    }

    @Override
    public Person getFirst() {
        TypedQuery<Person> query = em.createQuery("select p from Person p where p.id = 1",Person.class);
        return query.getSingleResult();
    }

    @Override
    public List<Person> getAll() {
        TypedQuery<Person> query = em.createQuery("select p from Person p", Person.class);
        return query.getResultList();
    }

    @Override
    public Person getByName(String name) {
        TypedQuery<Person> query = em.createQuery("select p from Person p where p.name = :name",Person.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
