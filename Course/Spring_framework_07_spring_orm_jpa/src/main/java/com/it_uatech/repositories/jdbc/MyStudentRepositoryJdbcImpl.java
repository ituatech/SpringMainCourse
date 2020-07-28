package com.it_uatech.repositories.jdbc;

import com.it_uatech.models.jdbc.Course;
import com.it_uatech.models.jdbc.MyStudent;
import com.it_uatech.repositories.jdbc.ext.MyStudentResultSetExtractor;
import com.it_uatech.repositories.jdbc.ext.StudentCourseRelation;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MyStudentRepositoryJdbcImpl implements MyStudentRepositoryJdbc {

    private final CourseRepositoryJdbc courseRepository;
    private final JdbcOperations op;

    @Override
    public List<MyStudent> findAllWithAllInfo() {
        List<Course> courses = courseRepository.findAllUsed();
        List<StudentCourseRelation> relations = getAllRelations();
        Map<Long, MyStudent> students = op.query("select ms.id, ms.name, a.id avatar_id, a.photo_url, e.id email_id, e.email " +
                        "from (my_students ms left join avatars a on " +
                        "ms.avatar_id = a.id) left join emails e on ms.id = e.student_id",
                new MyStudentResultSetExtractor());

        mergeStudentsInfo(students, courses, relations);
        return new ArrayList<>(Objects.requireNonNull(students).values());
    }

    private List<StudentCourseRelation> getAllRelations() {
        return op.query("select student_id, course_id from student_courses sc order by student_id, course_id",
                (rs, i) -> new StudentCourseRelation(rs.getLong(1), rs.getLong(2)));
    }

    private void mergeStudentsInfo(Map<Long, MyStudent> students, List<Course> courses, List<StudentCourseRelation> relations) {
        Map<Long, Course> coursesMap = courses.stream().collect(Collectors.toMap(Course::getId, c -> c));
        relations.forEach(r -> {
            if (students.containsKey(r.getStudentId()) && coursesMap.containsKey(r.getCourseId())) {
                students.get(r.getStudentId()).getCourses().add(coursesMap.get(r.getCourseId()));
            }
        });
    }


}
