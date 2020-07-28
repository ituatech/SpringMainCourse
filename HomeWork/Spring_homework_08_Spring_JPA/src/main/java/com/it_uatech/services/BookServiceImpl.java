package com.it_uatech.services;

import com.it_uatech.dao.BookRepository;
import com.it_uatech.domain.Author;
import com.it_uatech.domain.Book;
import com.it_uatech.domain.Genre;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public Book getById(int id) {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBook();
    }

    @Override
    public Book insert(String name, String description,
                       String authorFirstName, String authorSecondName,
                       String genreName) {
        Book book = new Book();
        book.setName(name);
        book.setDescription(description);
        Author author = new Author();
        author.setFirstName(authorFirstName);
        author.setSecondName(authorSecondName);
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setGenreName(genreName);
        book.getGenre().add(genre);
        genre.getBook().add(book);        // for many-to-many association for non-owner (mappedBy)
        bookRepository.insert(book);
        return book;
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
}
