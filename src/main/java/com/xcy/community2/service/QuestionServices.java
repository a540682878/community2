package com.xcy.community2.service;

import com.xcy.community2.dto.QuestionDTO;
import com.xcy.community2.mapper.UserMapper;
import com.xcy.community2.model.User;
import com.xcy.community2.mapper.QuestionMapper;
import com.xcy.community2.model.Question;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServices {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    Question question = new Question();


    public Question addQuesstion(@RequestParam(name = "title")String title,
                                 @RequestParam(name = "description")String description,
                                 @RequestParam(name = "tag")String tag,
                                 HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");


        question.setTitle(title);
        question.setDescription(description);
        question.setGmtCreat(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreat());
        question.setCreator(user.getId());
        question.setTag(tag);

        questionMapper.insertQuestion(question);
        return question;
    }

    public List<QuestionDTO> list(){
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

}
