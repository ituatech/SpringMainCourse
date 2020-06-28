package com.it_uatech.services;

import com.it_uatech.dao.GenreDao;
import com.it_uatech.domain.Genre;

import java.util.List;

public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public int count() {
        return genreDao.count();
    }

    @Override
    public Genre getById(int id) {
        return genreDao.getById(id);
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreDao.getAllGenre();
    }

    @Override
    public void insert(int id, String genreName) {
        genreDao.insert(new Genre(id,genreName));
    }

    @Override
    public void deleteById(int id) {
        genreDao.deleteById(id);
    }
}
