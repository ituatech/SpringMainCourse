package com.it_uatech.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Book {
    private final int id;
    private final String name;
    private final String description;
    private final Author author;
    private final Genre genre;

    public Book(int id, String name, String description,
                Author author, Genre genre){
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }


}
