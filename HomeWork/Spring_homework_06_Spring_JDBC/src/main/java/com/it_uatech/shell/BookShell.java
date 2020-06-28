package com.it_uatech.shell;

import com.it_uatech.services.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BookShell {

    private final BookService bookService;

    public BookShell(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("Book count")
    public int bookCount(){
        return  bookService.count();
    }

    @ShellMethod("Book get by id")
    public String bookGetById(@ShellOption int id){
        return bookService.getById(id).toString();
    }

    @ShellMethod("Book delete by id")
    public void bookDeleteById(@ShellOption int id){
        bookService.deleteById(id);
    }

    @ShellMethod("Book insert")
    public void bookInsert(@ShellOption int id, @ShellOption String name, @ShellOption String description,
                           @ShellOption int authorId, @ShellOption String firstName, @ShellOption String secondName,
                           @ShellOption int genreId, @ShellOption String genreName){
        bookService.insert(id, name, description, authorId, firstName, secondName, genreId, genreName);
    }

    @ShellMethod("Book list")
    public String bookList(){
        return  bookService.getAllBook().toString();
    }
}
