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
@Table(name="author_table")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="second_name", nullable = false)
    private String secondName;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Book> book = new HashSet<>();

}
