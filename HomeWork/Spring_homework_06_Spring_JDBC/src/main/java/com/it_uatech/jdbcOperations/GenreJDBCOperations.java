package com.it_uatech.jdbcOperations;

import com.it_uatech.dao.GenreDao;
import com.it_uatech.domain.Genre;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ConstantConditions")
@Repository("genreDao")
public class GenreJDBCOperations implements GenreDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final static GenreRowMapper genreRowMapper = new GenreRowMapper();

    public GenreJDBCOperations(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public int count() {
        final Map<String, Object> params = new HashMap<>(1);
        String query = "select count(*) from genre";
        return namedJdbcTemplate.queryForObject(query, params, Integer.class);
    }

    @Override
    public Genre getById(int id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        String query = "select * from genre where id = :id";
        try {
            return namedJdbcTemplate.queryForObject(query, params, genreRowMapper);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Genre> getAllGenre() {
        String query = "select * from genre";
        return namedJdbcTemplate.query(query, genreRowMapper);
    }

    @Override
    public void deleteById(int id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        String query = "delete from genre where id = :id";
        namedJdbcTemplate.update(query, params);
    }

    @Override
    public void insert(Genre genre) {
        final Map<String, Object> params = new HashMap<>(2);
        params.put("id", genre.getId());
        params.put("genreName", genre.getGenreName());
        String query = "insert into genre (id, genreName) values (:id, :genreName) on duplicate key update id= :id";
        namedJdbcTemplate.update(query,params);
    }

    private static class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Genre(resultSet.getInt("id"),
                    resultSet.getString("genreName"));
        }
    }

    public GenreRowMapper getGenreRowMapper() {
        return genreRowMapper;
    }
}
