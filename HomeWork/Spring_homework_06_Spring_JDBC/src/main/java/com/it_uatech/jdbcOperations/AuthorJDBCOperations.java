package com.it_uatech.jdbcOperations;

import com.it_uatech.dao.AuthorDao;
import com.it_uatech.domain.Author;
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
@Repository("authorDao")
public class AuthorJDBCOperations implements AuthorDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final static AuthorRowMapper authorRowMapper = new AuthorRowMapper();

    public AuthorJDBCOperations(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public int count() {
        final Map<String, Object> params = new HashMap<>(1);
        String query = "select count(*) from author";
        return namedJdbcTemplate.queryForObject(query, params, Integer.class);
    }

    @Override
    public Author getById(int id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        String query = "select * from author where id = :id";
        try {
            return namedJdbcTemplate.queryForObject(query, params, authorRowMapper);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Author> getAllAuthor() {
        String query = "select * from author";
        return namedJdbcTemplate.query(query, authorRowMapper);
    }

    @Override
    public void deleteById(int id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        String query = "delete from author where id = :id";
        namedJdbcTemplate.update(query, params);
    }

    @Override
    public void insert(Author author) {
        final Map<String, Object> params = new HashMap<>(3);
        params.put("id", author.getId());
        params.put("firstName", author.getFirstName());
        params.put("secondName", author.getSecondName());
        String query = "insert into author (id, firstName, secondName) values (:id, :firstName, :secondName) on duplicate key update id= :id";
        namedJdbcTemplate.update(query,params);
    }

    private static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Author(resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("secondName"));
        }
    }

    public AuthorRowMapper getAuthorRowMapper() {
        return authorRowMapper;
    }
}
