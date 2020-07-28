package com.it_uatech.repositories.mybatis;

import com.it_uatech.models.jdbc.EMail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmailRepositoryMyBatis {

    @Select("select * from emails where student_id = #{studentId}")
    List<EMail> getEmailsByStudentId(long studentId);
}
