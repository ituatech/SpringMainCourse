package com.it_uatech.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Genre {

    private final int id;
    private final String genreName;

    public Genre(int id, String genreName){
        this.id = id;
        this.genreName = genreName;
    }

    public int getId() {
        return id;
    }

    public String getGenreName() {
        return genreName;
    }
}
