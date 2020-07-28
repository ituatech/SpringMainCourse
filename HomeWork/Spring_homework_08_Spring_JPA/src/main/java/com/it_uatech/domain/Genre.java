package com.it_uatech.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="genre_table")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="genre_name", nullable = false, unique = true)
    private String genreName;

    @ManyToMany
    @JoinTable(name = "book_genre_table", joinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Book> book = new HashSet<>();

}
