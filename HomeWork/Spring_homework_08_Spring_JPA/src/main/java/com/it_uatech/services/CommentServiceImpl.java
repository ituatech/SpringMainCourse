package com.it_uatech.services;

import com.it_uatech.dao.BookRepository;
import com.it_uatech.dao.CommentRepository;
import com.it_uatech.domain.Book;
import com.it_uatech.domain.Comment;

import java.util.List;

public class CommentServiceImpl implements CommentService {

   private final CommentRepository commentRepository;
   private final BookRepository bookRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public long count() {
        return commentRepository.count();
    }

    @Override
    public Comment getById(int id) {
        return commentRepository.getById(id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.getAllComments();
    }

    @Override
    public Comment insert(int idBook, String comment) {
        Book book = bookRepository.getById(idBook);
        Comment comments = new Comment();
        comments.setBook(book);
        comments.setComment(comment);
        commentRepository.insert(comments);
        return comments;
    }

    @Override
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }
}
