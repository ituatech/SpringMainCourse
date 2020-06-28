package com.it_uatech.services;

import com.it_uatech.dao.AuthorDao;
import com.it_uatech.domain.Author;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public int count() {
        return authorDao.count();
    }

    @Override
    public Author getById(int id) {
        return authorDao.getById(id);
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorDao.getAllAuthor();
    }

    @Override
    public void insert(int id, String firstName, String secondName) {
        authorDao.insert(new Author(id,firstName,secondName));
    }

    @Override
    public void deleteById(int id) {
        authorDao.deleteById(id);
    }
}
