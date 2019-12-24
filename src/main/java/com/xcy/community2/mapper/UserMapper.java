package com.xcy.community2.mapper;

import com.xcy.community2.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id") //标识id为自增主键
    @Insert("insert into User (account_id,name,token,gmt_creat,gmt_modified) values (#{accoundId},#{name},#{token},#{gmtCreat},#{gmtModified})")
    public int insertUser(User user);
}