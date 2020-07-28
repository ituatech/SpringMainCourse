package com.it_uatech.dao;

import com.it_uatech.domain.Author;

import java.util.List;

public interface AuthorRepository {

    long count();

    Author getById(int id);

    List<Author> getAllAuthor();

    void deleteById(int id);

    void insert(Author author);

}
