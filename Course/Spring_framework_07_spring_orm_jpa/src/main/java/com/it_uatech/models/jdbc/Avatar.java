package com.it_uatech.models.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avatar {
    private long id;
    private String photoUrl;
}
