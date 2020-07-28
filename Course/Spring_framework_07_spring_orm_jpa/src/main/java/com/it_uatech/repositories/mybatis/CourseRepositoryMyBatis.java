package com.it_uatech.repositories.mybatis;

import com.it_uatech.models.jdbc.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseRepositoryMyBatis {

    @Select("select * from student_courses sc left join courses c on sc.course_id = c.id where sc.student_id = #{studentId}")
    List<Course> getCoursesByStudentId(long studentId);

}
