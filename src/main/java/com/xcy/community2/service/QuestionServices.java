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



    Question question = new Question();

    //添加发布的question到数据库
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

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    //将所有question封装成一个List<QuestionDTO>
    public List<QuestionDTO> questionPage(){
        //将所有question转换为泛型
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions){
            //通过findById获取user信息
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //复制question到questionDTO
            BeanUtils.copyProperties(question,questionDTO);
            //将user信息传递给questionDTO内的User对象
            questionDTO.setUser(user);
            //将questionDTO添加到ArrayList
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }


}
