package com.it_uatech.services;

import com.it_uatech.domain.Comment;

import java.util.List;

public interface CommentService {

    long count();

    Comment getById(int id);

    List<Comment> getAllComments();

    Comment insert(int idBook, String comment);

    void deleteById(int id);
}
