package com.it_uatech.services;

import com.it_uatech.dao.BookDao;
import com.it_uatech.domain.Author;
import com.it_uatech.domain.Book;
import com.it_uatech.domain.Genre;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public int count() {
        return bookDao.count();
    }

    @Override
    public Book getById(int id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAllBook() {
        return bookDao.getAllBook();
    }

    @Override
    public void insert(int id, String name, String description, int idAuthor, String firstName, String secondName, int idGenre, String genreName) {
        bookDao.insert(new Book(id,name,description,
                new Author(idAuthor,firstName,secondName),
                new Genre(idGenre,genreName)));
    }

    @Override
    public void deleteById(int id) {
        bookDao.deleteById(id);
    }
}
