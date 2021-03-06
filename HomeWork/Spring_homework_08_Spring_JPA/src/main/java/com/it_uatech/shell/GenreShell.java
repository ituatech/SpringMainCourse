package com.it_uatech.shell;

import com.it_uatech.services.GenreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class GenreShell {

    private final GenreService genreService;

    public GenreShell(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod("Genre count")
    public long genreCount(){
        return  genreService.count();
    }

    @ShellMethod("Genre get by id")
    public String genreGetById(@ShellOption int id){
        return genreService.getById(id).toString();
    }

    @ShellMethod("Genre delete by id")
    public void genreDeleteById(@ShellOption int id){
        genreService.deleteById(id);
    }

    @ShellMethod("Genre insert")
    public void genreInsert(@ShellOption int idBook, @ShellOption String genreName){
        genreService.insert(idBook, genreName);
    }

    @ShellMethod("Genre list")
    public String genreList(){
        return  genreService.getAllGenres().toString();
    }
}
