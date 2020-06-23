package com.it_uatech.dao;

import com.it_uatech.domain.Person;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class PersonDaoJdbc implements PersonDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations jdbcOperations;

    public PersonDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbc;
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count (*) from persons",Integer.class);
    }

    @Override
    public void insert(Person person) {
        jdbc.update("insert into persons (id, `name`) values (?, ?)",person.getId(),person.getName());
    }

    @Override
    public Person getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperations.queryForObject("select * from persons where id = :id",params,new PersonMapper());
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcOperations.update("delete from persons where id = :id",params);
    }

    @Override
    public List<Person> getAll() {
        return jdbc.query("select * from persons",new PersonMapper());
    }

    private static class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Person(id,name);
        }
    }
}
