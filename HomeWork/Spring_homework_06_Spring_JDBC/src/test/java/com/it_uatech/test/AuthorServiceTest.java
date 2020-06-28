package com.it_uatech.test;

import com.it_uatech.dao.AuthorDao;
import com.it_uatech.domain.Author;
import com.it_uatech.jdbcOperations.AuthorJDBCOperations;
import com.it_uatech.services.AuthorService;
import com.it_uatech.services.AuthorServiceImpl;
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
public class AuthorServiceTest {

    @Configuration
    static class AuthorServiceTestConfig {

        @Bean
        public AuthorDao getAuthorDao(NamedParameterJdbcTemplate namedJdbcTemplate){
            return new AuthorJDBCOperations(namedJdbcTemplate);
        }

        @Bean
        public AuthorService getAuthorService(AuthorDao authorDao) {
            return new AuthorServiceImpl(authorDao);
        }
    }

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorDao authorDao;

    @MockBean
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Test
    public void testAuthorCount(){
        final Map<String, Object> params = new HashMap<>(1);
        String query = "select count(*) from author";
        when(namedJdbcTemplate.queryForObject(query, params, Integer.class)).thenReturn(1);
        assertEquals(1, authorService.count());
    }

    @Test
    public void testGetById(){
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", 1);
        String query = "select * from author where id = :id";
        when(namedJdbcTemplate.queryForObject(query, params, ((AuthorJDBCOperations)authorDao).getAuthorRowMapper()))
                .thenReturn(new Author(1, "name1", "name2"));
        assertNotNull(authorService.getById(1));
        assertEquals(1, authorService.getById(1).getId());
        assertEquals("name1", authorService.getById(1).getFirstName());
        assertEquals("name2", authorService.getById(1).getSecondName());
    }

    @Test
    public void testGetAllAuthor() {
        String query = "select * from author";
        when(namedJdbcTemplate.query(query, ((AuthorJDBCOperations)authorDao).getAuthorRowMapper())).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<Author>(), authorService.getAllAuthor());
    }
}
