package com.it_uatech.repositories.jpa;

import com.it_uatech.models.jpa.MyStudentV2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MyStudentV2RepositoryJpaImpl implements MyStudentV2RepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<MyStudentV2> findAllWithEntityGraph() {
        EntityGraph<?> entityGraph = em.getEntityGraph("MyStudentWithAvatarAndEmails");
        TypedQuery<MyStudentV2> query = em.createQuery("select ms from MyStudentV2 ms", MyStudentV2.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<MyStudentV2> findAllWithJoinFetch() {
        return em.createQuery("select distinct ms from MyStudentV2 ms join fetch ms.avatar join fetch ms.emails", MyStudentV2.class).getResultList();
    }
}
