package com.it_uatech.services;

import com.it_uatech.domain.Genre;

import java.util.List;

public interface GenreService {

    long count();

    Genre getById(int id);

    List<Genre> getAllGenres();

    Genre insert(int idBook, String genreName);

    void deleteById(int id);

}
