package com.it_uatech.services;

import com.it_uatech.domain.Author;

import java.util.List;

public interface AuthorService {

    long count();

    Author getById(int id);

    List<Author> getAllAuthors();

    Author insert(String firstName, String secondName);

    void deleteById(int id);

}
