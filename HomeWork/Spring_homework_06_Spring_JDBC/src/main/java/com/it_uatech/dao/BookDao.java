package com.it_uatech.dao;

import com.it_uatech.domain.Book;

import java.util.List;

public interface BookDao {

    int count();

    Book getById(int id);

    List<Book> getAllBook();

    void deleteById(int id);

    void insert(Book book);

}
