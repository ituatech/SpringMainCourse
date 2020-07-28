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
    public long bookCount(){
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
    public void bookInsert(@ShellOption String name, @ShellOption String description,
                           @ShellOption String authorFirstName, @ShellOption String authorSecondName,
                           @ShellOption String genreName){
        bookService.insert(name, description, authorFirstName, authorSecondName, genreName);
    }

    @ShellMethod("Book list")
    public String bookList(){
        return  bookService.getAllBooks().toString();
    }
}
