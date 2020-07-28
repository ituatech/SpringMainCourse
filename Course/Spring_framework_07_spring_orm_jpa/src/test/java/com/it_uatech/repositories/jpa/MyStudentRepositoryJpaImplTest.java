package com.it_uatech.repositories.jpa;

import com.it_uatech.models.jpa.MyStudent;
import com.it_uatech.models.jpa.common.Avatar;
import com.it_uatech.models.jpa.common.Course;
import com.it_uatech.models.jpa.common.EMail;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;


import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы со студентами ")
@DataJpaTest
@Import({MyStudentRepositoryJpaImpl.class, CourseRepositoryJpaImpl.class})
class MyStudentRepositoryJpaImplTest {

    @Autowired
    private MyStudentRepositoryJpaImpl repositoryJpa;

    @Autowired
    private CourseRepositoryJpaImpl courseRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" должен загружать информацию о нужном студенте")
    @Test
    void shouldFindExpectedStudentById() {
        val optionalActualStudent = repositoryJpa.findById(1L);
        val expectedStudent = em.find(MyStudent.class, 1L);
        assertThat(optionalActualStudent).isPresent().get().usingRecursiveComparison().isEqualTo(expectedStudent);
    }

    @DisplayName("должен загружать список всех студентов с полной информацией о них")
    @Test
    void shouldReturnCorrectStudentsListWithAllInfo() {
        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val students = repositoryJpa.findAll();
        assertThat(students).isNotNull().hasSize(10).allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
    }

    @DisplayName(" должен корректно сохранять всю информацию о студенте")
    @Test
    void shouldSaveAllStudentInfo() {
        val avatar = new Avatar(0, "где-то там");
        val email = new EMail(0, "any@mail.com");
        val emails = Collections.singletonList(email);

        val course = new Course(0, "Spring");
        val courses = Collections.singletonList(course);
        courseRepositoryJpa.save(course);


        val vasya = new MyStudent(0, "Vasya", avatar, emails, courses);
        repositoryJpa.save(vasya);
        assertThat(vasya.getId()).isGreaterThan(0);

        val actualStudent = em.find(MyStudent.class, vasya.getId());
        assertThat(actualStudent).isNotNull().matches(s -> !s.getName().equals(""))
                .matches(s -> s.getCourses() != null && s.getCourses().size() > 0 && s.getCourses().get(0).getId() > 0)
                .matches(s -> s.getAvatar() != null)
                .matches(s -> s.getEmails() != null && s.getEmails().size() > 0);
    }
}