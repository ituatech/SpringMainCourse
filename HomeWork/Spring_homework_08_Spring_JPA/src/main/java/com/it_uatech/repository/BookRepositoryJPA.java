package com.it_uatech.repository;

import com.it_uatech.dao.BookRepository;
import com.it_uatech.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("bookDao")
@Transactional
public class BookRepositoryJPA implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("select count(b) from Book b", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Book getById(int id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.id = :id", Book.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getAllBook() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        em.remove(getById(id));
    }

    @Override
    public void insert(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }
}