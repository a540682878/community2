package com.xcy.community2.mapper;

import com.xcy.community2.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id") //标识id为自增主键
    @Insert("insert into question (title,description,gmt_creat,gmt_modified,creator,tag,avatar_url) value (#{title},#{description},#{gmtCreat},#{gmtModified},#{creator},#{tag},#{avatarUrl})")
    public void insertQuestion(Question question);

    @Select("select * from question")
    public List<Question> list();

    @Select("select * from question where creator=#{creator}")
    public List<Question> questionByCreator(Long creator);

    //通过question的id获取question
    @Select("select * from question where id=#{id}")
    public Question getQuestionById(Integer id);

    @Update("update question set title=#{title}, description=#{description}, tag=#{tag}, gmt_modified=#{gmtModified} where id=#{id}")
    void updateQuestion(Question question);
}
