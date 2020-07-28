package com.it_uatech.services;

import com.it_uatech.dao.BookRepository;
import com.it_uatech.dao.GenreRepository;
import com.it_uatech.domain.Book;
import com.it_uatech.domain.Genre;

import java.util.List;

public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public GenreServiceImpl(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public long count() {
        return genreRepository.count();
    }

    @Override
    public Genre getById(int id) {
        return genreRepository.getById(id);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.getAllGenre();
    }

    @Override
    public Genre insert(int idBook, String genreName) {
        Book book = bookRepository.getById(idBook);
        Genre genre = new Genre();
        genre.setGenreName(genreName);
        genre.getBook().add(book);
        book.getGenre().add(genre);
        genreRepository.insert(genre);
        return genre;
    }

    @Override
    public void deleteById(int id) {
        genreRepository.deleteById(id);
    }
}
