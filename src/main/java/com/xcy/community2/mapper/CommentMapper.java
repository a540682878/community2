package com.xcy.community2.mapper;

import com.xcy.community2.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CommentMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id") //标识id为自增主键
    @Insert("insert into comment (parent_id,type,commentator,gmt_creat,gmt_modified,like_count) value (#{parentId},#{type},#{commentator},#{gmtCreat},#{gmtModified},#{likeCount})")
    public void insertComment(Comment comment);

}
