package com.it_uatech.repositories.mybatis;

import com.it_uatech.models.jdbc.Avatar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AvatarRepositoryMyBatis {
    @Select("select * from avatars where id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "photoUrl", column = "photo_url")
    })
    Avatar getAvatarById(long id);

}
