package com.it_uatech.repository;

import com.it_uatech.dao.CommentRepository;
import com.it_uatech.domain.Book;
import com.it_uatech.domain.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("commentDao")
@Transactional
public class CommentRepositoryJPA implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("select count(c) from Comment c", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Comment getById(int id) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.id=:id", Comment.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public List<Comment> getAllComments() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        em.remove(getById(id));
    }

    @Override
    public void insert(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
    }
}
