package com.xcy.community2.service;

import com.github.pagehelper.Page;
import com.xcy.community2.mapper.QuestionMapper;
import com.xcy.community2.model.Question;
import org.springframework.beans.factory.annotation.Autowired;

public class QuestionService2 {

    @Autowired
    private QuestionMapper questionMapper;

    public Page<Question> questionPage(){
        return (Page<Question>) questionMapper.list();
    }
}
