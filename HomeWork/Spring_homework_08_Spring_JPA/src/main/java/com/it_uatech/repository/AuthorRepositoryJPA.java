package com.it_uatech.repository;

import com.it_uatech.dao.AuthorRepository;
import com.it_uatech.domain.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("authorDao")
@Transactional
public class AuthorRepositoryJPA implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("select count(a) from Author a",Long.class);
        return query.getSingleResult();
    }

    @Override
    public Author getById(int id) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.id=:id", Author.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAllAuthor() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        em.remove(getById(id));
    }

    @Override
    public void insert(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
        } else {
            em.merge(author);
        }
    }
}
