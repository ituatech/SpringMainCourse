package com.it_uatech.services;

import com.it_uatech.dao.AuthorRepository;
import com.it_uatech.domain.Author;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public long count() {
        return authorRepository.count();
    }

    @Override
    public Author getById(int id) {
        return authorRepository.getById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthor();
    }

    @Override
    public Author insert(String firstName, String secondName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setSecondName(secondName);
        authorRepository.insert(author);
        return author;
    }

    @Override
    public void deleteById(int id) {
        authorRepository.deleteById(id);
    }
}
