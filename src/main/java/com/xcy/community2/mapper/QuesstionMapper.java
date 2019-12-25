package com.xcy.community2.mapper;

import com.xcy.community2.model.Quesstion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuesstionMapper {

    @Insert("insert into quesstion (title,description,gmt_creat,gmt_modified,creator,tag) value (#{title},#{description},#{gmtCreat},#{gmtModified},#{creator},#{tag})")
    public void insertQuestion(Quesstion quesstion);

}
