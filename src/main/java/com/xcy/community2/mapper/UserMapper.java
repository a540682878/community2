package com.xcy.community2.mapper;

import com.xcy.community2.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id") //标识id为自增主键
    @Insert("insert into User (account_id,name,token,gmt_creat,gmt_modified,avatar_url) values (#{accoundId},#{name},#{token},#{gmtCreat},#{gmtModified},#{avatarUrl})")
    public int insertUser(User user);

    @Select("select * from User where token=#{token}")
    public User findByToken(@Param( "token") String token);
}
