package com.it_uatech.test;

import com.it_uatech.dao.GenreDao;
import com.it_uatech.domain.Genre;
import com.it_uatech.jdbcOperations.GenreJDBCOperations;
import com.it_uatech.services.GenreService;
import com.it_uatech.services.GenreServiceImpl;
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
public class GenreServiceTest {

    @Configuration
    static class GenreServiceTestConfig {

        @Bean
        public GenreDao getGenreDao(NamedParameterJdbcTemplate namedJdbcTemplate){
            return new GenreJDBCOperations(namedJdbcTemplate);
        }

        @Bean
        public GenreService getGenreService(GenreDao genreDao) {
            return new GenreServiceImpl(genreDao);
        }
    }

    @Autowired
    GenreService genreService;

    @Autowired
    GenreDao genreDao;

    @MockBean
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Test
    public void testGenreCount(){
        final Map<String, Object> params = new HashMap<>(1);
        String query = "select count(*) from genre";
        when(namedJdbcTemplate.queryForObject(query, params, Integer.class)).thenReturn(1);
        assertEquals(1, genreService.count());
    }

    @Test
    public void testGetById(){
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", 1);
        String query = "select * from genre where id = :id";
        when(namedJdbcTemplate.queryForObject(query, params, ((GenreJDBCOperations) genreDao).getGenreRowMapper()))
                .thenReturn(new Genre(1, "name1"));
        assertNotNull(genreService.getById(1));
        assertEquals(1, genreService.getById(1).getId());
        assertEquals("name1", genreService.getById(1).getGenreName());
    }

    @Test
    public void testGetAllGenre() {
        String query = "select * from genre";
        when(namedJdbcTemplate.query(query, ((GenreJDBCOperations) genreDao).getGenreRowMapper())).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<Genre>(), genreService.getAllGenre());
    }
}
