package com.xcy.community2.service;

import com.xcy.community2.bean.User;
import com.xcy.community2.dto.GithubUser;
import com.xcy.community2.mapper.QuesstionMapper;
import com.xcy.community2.mapper.UserMapper;
import com.xcy.community2.model.Quesstion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.UUID;

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
