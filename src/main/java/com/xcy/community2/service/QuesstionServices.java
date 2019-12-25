package com.xcy.community2.service;

import com.xcy.community2.model.User;
import com.xcy.community2.mapper.QuesstionMapper;
import com.xcy.community2.model.Quesstion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Service
public class QuesstionServices {

    @Autowired
    QuesstionMapper quesstionMapper;

    Quesstion quesstion = new Quesstion();


    public Quesstion addQuesstion(@RequestParam(name = "title")String title,
                                  @RequestParam(name = "description")String description,
                                  @RequestParam(name = "tag")String tag,
                                  HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");


        quesstion.setTitle(title);
        quesstion.setDescription(description);
        quesstion.setGmtCreat(System.currentTimeMillis());
        quesstion.setGmtModified(quesstion.getGmtCreat());
        quesstion.setCreator(user.getId());
        quesstion.setTag(tag);

        quesstionMapper.insertQuestion(quesstion);
        return quesstion;
    }



}
