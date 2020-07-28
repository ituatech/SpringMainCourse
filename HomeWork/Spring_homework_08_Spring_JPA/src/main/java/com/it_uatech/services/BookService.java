package com.it_uatech.services;

import com.it_uatech.domain.Book;

import java.util.List;

public interface BookService {

    long count();

    Book getById(int id);

    List<Book> getAllBooks();

    Book insert(String name, String description,
                String authorFirstName, String authorSecondName,
                String genreName);

    void deleteById(int id);

}
