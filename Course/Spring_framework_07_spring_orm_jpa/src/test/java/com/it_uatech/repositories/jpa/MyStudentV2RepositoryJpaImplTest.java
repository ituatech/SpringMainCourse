package com.it_uatech.repositories.jpa;

import com.it_uatech.models.jpa.MyStudentV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий v2 на основе Jpa для работы со студентами ")
@DataJpaTest
@Import({MyStudentV2RepositoryJpaImpl.class})
class MyStudentV2RepositoryJpaImplTest {


    @Autowired
    private MyStudentV2RepositoryJpaImpl repositoryJpa;

    @DisplayName(" с помощью EntityGraph должен загружать список всех студентов с полной информацией о них")
    @Test
    void usingEntityGraphShouldReturnCorrectStudentsListWithWithAllInfo() {
        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        List<MyStudentV2> students = repositoryJpa.findAllWithEntityGraph();
        assertThat(students).isNotNull().hasSize(10).allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");

    }

    @DisplayName(" с помощью 'join fetch' должен загружать список всех студентов с полной информацией о них")
    @Test
    void usingJoinFetchShouldReturnCorrectStudentsListWithWithAllInfo() {
        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        List<MyStudentV2> students = repositoryJpa.findAllWithJoinFetch();
        assertThat(students).isNotNull().hasSize(10).allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
    }
}