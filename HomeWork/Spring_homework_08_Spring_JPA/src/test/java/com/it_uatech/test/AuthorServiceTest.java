package com.it_uatech.test;

import com.it_uatech.domain.Author;
import com.it_uatech.domain.Book;
import com.it_uatech.services.AuthorService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import(com.it_uatech.config.Configurations.class)
@ComponentScan("com.it_uatech.repository")
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TestEntityManager em;

    @Test
    public void retrieveCorrectCountOfATable(){
        assertThat(authorService).matches((authorService)-> authorService.count()==2);
    }

    @Test
    public void shouldFindExpectedAuthorById() {
        val optionalActualStudent = authorService.getById(1);
        val expectedStudent = em.find(Author.class, 1);
        assertThat(optionalActualStudent).isNotNull().usingRecursiveComparison().isEqualTo(expectedStudent);
        assertThat(optionalActualStudent.getFirstName()).isEqualTo("firstName1");
        assertThat(optionalActualStudent.getSecondName()).isEqualTo("secondName1");
    }

    @Test
    void shouldReturnCorrectStudentsListWithAllInfo() {
        val authors = authorService.getAllAuthors();
        assertThat(authors).isNotNull().hasSize(2).allMatch(author -> !author.getFirstName().equals("")&!author.getSecondName().equals(""))
                .allMatch(author -> author.getBook().size()==1);
    }

    @Test
    public void shouldSaveAllAuthorInfo() {
        Author author = authorService.insert("authorFirstName","authorSecondName");
        assertThat(author).isNotNull();
        int id = author.getId();
        assertThat(id).isGreaterThan(0);

        val actualAuthor = em.find(Author.class, id);
        assertThat(actualAuthor).isNotNull().matches(a->a.getFirstName().equals(author.getFirstName())&a.getSecondName().equals(author.getSecondName()));
    }

    @Test
    public void shouldDeleteAuthorAndBook() {
        assertThat(em.find(Book.class,1)).isNotNull();
        assertThat(em.find(Author.class,1)).isNotNull();
        authorService.deleteById(1);
        assertThat(em.find(Author.class,1)).isNull();
        assertThat(em.find(Book.class,1)).isNull();
    }
}