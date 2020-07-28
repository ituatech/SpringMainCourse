package com.it_uatech.repositories.jdbc.ext;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StudentCourseRelation {
    private final long studentId;
    private final long courseId;
}
