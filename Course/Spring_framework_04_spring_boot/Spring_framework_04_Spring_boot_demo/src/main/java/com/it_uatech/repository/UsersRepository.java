package com.it_uatech.repository;

import com.it_uatech.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Integer> {
    @SuppressWarnings("NullableProblems")
    List<User> findAll();
}
