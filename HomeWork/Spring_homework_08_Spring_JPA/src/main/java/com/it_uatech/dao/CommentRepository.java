package com.it_uatech.dao;

import com.it_uatech.domain.Comment;

import java.util.List;

public interface CommentRepository {

    long count();

    Comment getById(int id);

    List<Comment> getAllComments();

    void deleteById(int id);

    void insert(Comment comment);
}
