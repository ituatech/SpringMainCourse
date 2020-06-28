package com.it_uatech.jdbcOperations;

import com.it_uatech.dao.AuthorDao;
import com.it_uatech.dao.BookDao;
import com.it_uatech.dao.GenreDao;
import com.it_uatech.domain.Author;
import com.it_uatech.domain.Book;
import com.it_uatech.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ConstantConditions")
@Repository("bookDao")
public class BookJDBCOperations implements BookDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final BookRowMapper bookRowMapper = new BookRowMapper();

    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookJDBCOperations(NamedParameterJdbcTemplate namedJdbcTemplate, AuthorDao authorDao, GenreDao genreDao) {
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public int count() {
        final Map<String, Object> params = new HashMap<>(1);
        String query = "select count(*) from book";
        return namedJdbcTemplate.queryForObject(query, params, Integer.class);
    }

    @Override
    public Book getById(int id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        String query = "select * from book where id = :id";
        return namedJdbcTemplate.queryForObject(query, params, bookRowMapper);
    }

    @Override
    public List<Book> getAllBook() {
        String query = "select * from book";
        return namedJdbcTemplate.query(query, bookRowMapper);
    }

    @Override
    public void deleteById(int id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        String query = "delete from book where id = :id";
        namedJdbcTemplate.update(query, params);
    }

    @Override
    public void insert(Book book) {
        int author_id = book.getAuthor().getId();
        int genre_id = book.getGenre().getId();
        Author authorFromDB = authorDao.getById(author_id);
        if (authorFromDB==null){
            authorDao.insert(book.getAuthor());
        }
        Genre genreFromDB = genreDao.getById(genre_id);
        if (genreFromDB==null){
            genreDao.insert(book.getGenre());
        }
        final Map<String, Object> params = new HashMap<>(5);
        params.put("id", book.getId());
        params.put("name", book.getName());
        params.put("description", book.getDescription());
        params.put("author_id",author_id);
        params.put("genre_id",genre_id);
        String query = "insert into book (id, name, description, author_id, genre_id) values (:id, :name, :description, :author_id, :genre_id) on duplicate key update id= :id";
        namedJdbcTemplate.update(query,params);
    }

    public AuthorDao getAuthorDao() {
        return authorDao;
    }

    public GenreDao getGenreDao() {
        return genreDao;
    }

    private class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Author authorFromDB = getAuthorDao().getById(resultSet.getInt("author_id"));
            Genre genreFromDB = getGenreDao().getById(resultSet.getInt("genre_id"));
            return new Book(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    authorFromDB,
                    genreFromDB
            );
        }
    }

    public BookRowMapper getBookRowMapper() {
        return bookRowMapper;
    }
}