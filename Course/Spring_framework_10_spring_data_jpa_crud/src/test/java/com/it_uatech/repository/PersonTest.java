package com.it_uatech.repository;


import com.it_uatech.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldFindPersonByName(){
        Person expectedPerson = new Person("Vova");
        em.persist(expectedPerson);
        Person actualPerson = personRepository.findByName("Vova");
        assertThat(actualPerson).isNotNull().isEqualToComparingFieldByField(expectedPerson);
    }
}
