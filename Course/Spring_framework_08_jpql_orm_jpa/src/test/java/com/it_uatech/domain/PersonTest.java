package com.it_uatech.domain;

import com.it_uatech.repostory.PersonRepositoryJpa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(PersonRepositoryJpa.class)
public class PersonTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private PersonRepositoryJpa repository;

    @Test
    public void saveAndGet() {
        Person person = new Person("Ivan");
        Person fromDb = em.persistAndFlush(person);
        assertThat(fromDb.getId()).isNotZero();
        assertThat(fromDb.getName()).isEqualTo(person.getName());

        Person fromDBWithRepo = repository.getById(fromDb.getId());
        assertThat(fromDBWithRepo.getName()).isEqualTo(person.getName());
    }
}
