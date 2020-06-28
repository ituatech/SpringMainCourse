package com.it_uatech.test;

import com.it_uatech.dao.AuthorDao;
import com.it_uatech.dao.BookDao;
import com.it_uatech.dao.GenreDao;
import com.it_uatech.domain.Author;
import com.it_uatech.domain.Book;
import com.it_uatech.domain.Genre;
import com.it_uatech.jdbcOperations.AuthorJDBCOperations;
import com.it_uatech.jdbcOperations.BookJDBCOperations;
import com.it_uatech.jdbcOperations.GenreJDBCOperations;
import com.it_uatech.services.BookService;
import com.it_uatech.services.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    @Configuration
    static class BookServiceTestConfig {

        @Bean
        public AuthorDao getAuthorDao(NamedParameterJdbcTemplate namedJdbcTemplate){
            return new AuthorJDBCOperations(namedJdbcTemplate);
        }

        @Bean
        public GenreDao getGenreDao(NamedParameterJdbcTemplate namedJdbcTemplate){
            return new GenreJDBCOperations(namedJdbcTemplate);
        }

        @Bean
        public BookDao getBookDao(NamedParameterJdbcTemplate namedJdbcTemplate, AuthorDao authorDao, GenreDao genreDao){
            return new BookJDBCOperations(namedJdbcTemplate, authorDao, genreDao);
        }

        @Bean
        public BookService getBookService(BookDao bookDao) {
            return new BookServiceImpl(bookDao);
        }
    }

    @Autowired
    BookService bookService;

    @Autowired
    BookDao bookDao;

    @MockBean
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Test
    public void testBookCount(){
        final Map<String, Object> params = new HashMap<>(1);
        String query = "select count(*) from book";
        when(namedJdbcTemplate.queryForObject(query, params, Integer.class)).thenReturn(1);
        assertEquals(1, bookService.count());
    }

    @Test
    public void testGetById(){
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", 1);
        String query = "select * from book where id = :id";
        Author author = new Author(1,"firstName1", "secondName1");
        Genre genre = new Genre(1, "genre1");
        when(namedJdbcTemplate.queryForObject(query, params, ((BookJDBCOperations)bookDao).getBookRowMapper()))
                .thenReturn(new Book(1, "nameBook", "description",
                        author,
                        genre));
        assertNotNull(bookService.getById(1));
        assertEquals(1, bookService.getById(1).getId());
        assertEquals("nameBook", bookService.getById(1).getName());
        assertEquals("description", bookService.getById(1).getDescription());
        assertEquals(author, bookService.getById(1).getAuthor());
        assertEquals(genre, bookService.getById(1).getGenre());
    }

    @Test
    public void testGetAllBook() {
        String query = "select * from book";
        when(namedJdbcTemplate.query(query, ((BookJDBCOperations) bookDao).getBookRowMapper())).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<Book>(), bookService.getAllBook());
    }
}
