package com.it_uatech.repositories.mybatis;

import com.it_uatech.models.jdbc.Avatar;
import com.it_uatech.models.jdbc.MyStudent;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface MyStudentRepositoryMyBatis {
    @Select("select * from my_students")
    @Results(id = "studentAllMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "avatar", column = "avatar_id", javaType = Avatar.class,
                    one = @One(select = "com.it_uatech.repositories.mybatis.AvatarRepositoryMyBatis.getAvatarById", fetchType = FetchType.EAGER)),
            @Result(property = "emails", column = "id", javaType = List.class,
                    many = @Many(select = "com.it_uatech.repositories.mybatis.EmailRepositoryMyBatis.getEmailsByStudentId", fetchType = FetchType.EAGER)),
            @Result(property = "courses", column = "id", javaType = List.class,
                    many = @Many(select = "com.it_uatech.repositories.mybatis.CourseRepositoryMyBatis.getCoursesByStudentId", fetchType = FetchType.EAGER))
    })
    List<MyStudent> findAllWithAllInfo();

    @Select("select * from my_students where id = #{id}")
    @ResultMap("studentAllMap")
    MyStudent findById(long id);

    @Select("select count(*) as students_count from my_students")
    long getStudentsCount();

    @Insert("insert into my_students(name, avatar_id) values (#{name}, #{avatar.id})")
    void insert(MyStudent student);

    @Update("update my_students set name = #{name} where id = #{id}")
    void updateName(MyStudent student);

    @Delete("delete from my_students where id = #{id}")
    void deleteById(long id);

}
