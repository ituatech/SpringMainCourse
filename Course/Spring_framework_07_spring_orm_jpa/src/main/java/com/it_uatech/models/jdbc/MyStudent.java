package com.it_uatech.models.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyStudent {
    private long id;
    private String name;
    private Avatar avatar;
    private List<EMail> emails;
    private List<Course> courses;
}
