package com.it_uatech.repository;

import com.it_uatech.dao.GenreRepository;
import com.it_uatech.domain.Genre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("genreDao")
@Transactional
public class GenreRepositoryJPA implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("select count(g) from Genre g", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Genre getById(int id) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.id=:id", Genre.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public List<Genre> getAllGenre() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        em.remove(getById(id));
    }

    @Override
    public void insert(Genre genre) {
        if (genre.getId() <= 0) {
            em.persist(genre);
        } else {
            em.merge(genre);
        }
    }
}
