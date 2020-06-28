package com.it_uatech.dao;

import com.it_uatech.domain.Author;

import java.util.List;

public interface AuthorDao {

    int count();

    Author getById(int id);

    List<Author> getAllAuthor();

    void deleteById(int id);

    void insert(Author author);

}
