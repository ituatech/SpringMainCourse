package com.it_uatech.config;

import com.it_uatech.repository.AuthorRepositoryJPA;
import com.it_uatech.repository.BookRepositoryJPA;
import com.it_uatech.repository.CommentRepositoryJPA;
import com.it_uatech.repository.GenreRepositoryJPA;
import com.it_uatech.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

    @Bean
    public AuthorService authorService(AuthorRepositoryJPA authorDao){
        return new AuthorServiceImpl(authorDao);
    }
    @Bean
    public BookService bookService(BookRepositoryJPA bookDao){
        return new BookServiceImpl(bookDao);
    }
    @Bean
    public GenreService genreService(GenreRepositoryJPA genreDao, BookRepositoryJPA bookDao){
        return new GenreServiceImpl(genreDao, bookDao);
    }
    @Bean
    public CommentService commentService(CommentRepositoryJPA commentDao, BookRepositoryJPA bookDao) {
        return new CommentServiceImpl(commentDao, bookDao);
    }
}
