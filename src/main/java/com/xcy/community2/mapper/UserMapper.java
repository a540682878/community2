package com.xcy.community2.mapper;

import com.xcy.community2.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id") //标识id为自增主键
    @Insert("insert into user (account_id,name,token,gmt_creat,gmt_modified,avatar_url) value (#{accountId},#{name},#{token},#{gmtCreat},#{gmtModified},#{avatarUrl})")
    public int insertUser(User user);

    @Select("select * from user where token=#{token}")
    public User findByToken(@Param( "token") String token);

    //通过acctoundId获取到user信息

    @Select("select * from user where account_id=#{accountId}")
    public User findUserByAccountId(Long accountId);

    @Update("update user set name=#{name}, token=#{token}, gmt_modified=#{gmtModified}, avatar_url=#{avatarUrl} where id=#{id}")
    public void updateUser(User user);
}
