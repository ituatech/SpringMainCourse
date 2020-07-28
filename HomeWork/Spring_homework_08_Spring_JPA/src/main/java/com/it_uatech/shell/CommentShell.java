package com.it_uatech.shell;

import com.it_uatech.services.CommentService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CommentShell {

    private final CommentService commentService;

    public CommentShell(CommentService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod("Comment count")
    public long commentCount(){
        return  commentService.count();
    }

    @ShellMethod("Comment get by id")
    public String commentGetById(@ShellOption int id){
        return commentService.getById(id).toString();
    }

    @ShellMethod("Comment delete by id")
    public void commentDeleteById(@ShellOption int id){
        commentService.deleteById(id);
    }

    @ShellMethod("Comment insert")
    public void commentInsert(@ShellOption int idBook, @ShellOption String comment){
        commentService.insert(idBook, comment);
    }

    @ShellMethod("Comment list")
    public String commentList(){
        return  commentService.getAllComments().toString();
    }
}
