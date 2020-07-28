package com.it_uatech.repository;

import com.it_uatech.domain.Email;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmailRepository extends PagingAndSortingRepository<Email,Integer> {

    List<Email> findAll(Sort sort);

    // custom query
    @Query("select e from Email e where e.id=:id")
    Email get(int id);
}
