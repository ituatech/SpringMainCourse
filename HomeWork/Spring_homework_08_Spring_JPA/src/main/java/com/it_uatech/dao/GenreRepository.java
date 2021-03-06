package com.it_uatech.dao;

import com.it_uatech.domain.Genre;

import java.util.List;

public interface GenreRepository {

    long count();

    Genre getById(int id);

    List<Genre> getAllGenre();

    void deleteById(int id);

    void insert(Genre genre);

}
