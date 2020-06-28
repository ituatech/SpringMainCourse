package com.it_uatech.services;

import com.it_uatech.domain.Book;

import java.util.List;

public interface BookService {

    int count();

    Book getById(int id);

    List<Book> getAllBook();

    void insert(int id, String name, String description,
                int idAuthor, String firstName, String secondName,
                int idGenre, String genreName);

    void deleteById(int id);

}
