package com.it_uatech.services;

import com.it_uatech.domain.Genre;

import java.util.List;

public interface GenreService {

    int count();

    Genre getById(int id);

    List<Genre> getAllGenre();

    void insert(int id, String genreName);

    void deleteById(int id);

}
