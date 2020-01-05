package com.xcy.community2.service;

import com.xcy.community2.dto.QuestionDTO;
import com.xcy.community2.mapper.UserMapper;
import com.xcy.community2.model.User;
import com.xcy.community2.mapper.QuestionMapper;
import com.xcy.community2.model.Question;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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
        question.setTag(tag);
        question.setCreator(user.getAccountId());
        question.setGmtCreat(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreat());
        question.setAvatarUrl(user.getAvatarUrl());
        questionMapper.insertQuestion(question);

        return question;
    }

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    //将所有question封装成一个List<QuestionDTO>
    public List<QuestionDTO> questionPage(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        //将所有question转换为泛型
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions){
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

    public List<Question> list(){
        return questionMapper.list();
    }


    //通过user的accoundId获取question
    public List<Question> myQuestionList(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        List<Question> questionList = questionMapper.questionByCreator(user.getAccountId());
        return questionList;
    }

    //获取单个question,并将数据复制给questionDTO
    public QuestionDTO getQuestionDTOById(Integer id){

        Question question = questionMapper.getQuestionById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        //复制question到questionDTO
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findUserByAccountId(questionDTO.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    //修改question
    public QuestionDTO updateQuestion(@RequestParam(name = "title")String title,
                                      @RequestParam(name = "description")String description,
                                      @RequestParam(name = "tag")String tag,
                                      @RequestParam(name = "id")Integer id){
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtModified(System.currentTimeMillis());
        question.setId(id);
        questionMapper.updateQuestion(question);
        //复制question到questionDTO
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }

    //增加阅读数
    public void incView(@RequestParam(name = "id") Integer id) {
        Question question = questionMapper.getQuestionById(id);


        question.setViewCount(question.getViewCount()+1);

        questionMapper.updatequestionViewcount(question);
    }
}
