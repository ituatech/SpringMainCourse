package com.it_uatech.config;

import com.it_uatech.jdbcOperations.AuthorJDBCOperations;
import com.it_uatech.jdbcOperations.BookJDBCOperations;
import com.it_uatech.jdbcOperations.GenreJDBCOperations;
import com.it_uatech.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

    @Bean
    public AuthorService authorService(AuthorJDBCOperations authorDao){
        return new AuthorServiceImpl(authorDao);
    }
    @Bean
    public BookService bookService(BookJDBCOperations bookDao){
        return new BookServiceImpl(bookDao);
    }
    @Bean
    public GenreService genreService(GenreJDBCOperations genreDao){
        return new GenreServiceImpl(genreDao);
    }

}
