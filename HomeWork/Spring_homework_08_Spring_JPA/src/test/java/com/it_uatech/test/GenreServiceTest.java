package com.it_uatech.test;

import com.it_uatech.domain.Book;
import com.it_uatech.domain.Genre;
import com.it_uatech.services.GenreService;
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
public class GenreServiceTest {

    @Autowired
    GenreService genreService;

    @Autowired
    private TestEntityManager em;

    @Test
    public void retrieveCorrectCountOfATable() {
        assertThat(genreService).matches(genreService -> genreService.count() == 2);
    }

    @Test
    public void shouldFindExpectedGenreById() {
        val optionalActualGenre = genreService.getById(1);
        val expectedGenre = em.find(Genre.class, 1);
        val expectedBook = em.find(Book.class,1);
        assertThat(optionalActualGenre).isNotNull().usingRecursiveComparison().isEqualTo(expectedGenre);
        assertThat(optionalActualGenre).matches(g -> g.getGenreName().equals("genre1") && g.getBook().size() == 1 && g.getBook().contains(expectedBook));
    }

    @Test
    void shouldReturnCorrectGenreListWithAllInfo() {
        val genres = genreService.getAllGenres();
        assertThat(genres).isNotNull().hasSize(2).allMatch(genre -> !genre.getGenreName().equals(""), "all genres exist")
                .allMatch(genre -> genre.getBook().size() == 1, "all genres belong to books");
    }

    @Test
    public void shouldSaveAllGenreInfo() {
        Genre genre = genreService.insert(1, "newGenre");
        assertThat(genre).isNotNull();
        int id = genre.getId();
        assertThat(id).isGreaterThan(0);
        val actualGenre = em.find(Genre.class, id);
        assertThat(actualGenre).isNotNull().matches(g -> g.getGenreName().equals(genre.getGenreName()), "genre matches")
                .matches(g -> g.getBook().size() == 1, "book exists");
    }

    @Test
    public void shouldDeleteGenre() {
        assertThat(em.find(Genre.class,1)).isNotNull();
        genreService.deleteById(1);
        assertThat(em.find(Genre.class,1)).isNull();
    }
}
