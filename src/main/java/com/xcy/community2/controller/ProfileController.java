package com.xcy.community2.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xcy.community2.mapper.QuestionMapper;
import com.xcy.community2.service.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionServices questionServices;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          @RequestParam(defaultValue = "1")Integer pn,
                          @RequestParam(defaultValue = "5")Integer pageSize,
                          HttpServletRequest request,
                          Model model){

        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的发布");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回答");
        }
        Page p = PageHelper.startPage(pn,pageSize);
        PageInfo info = new PageInfo<>(questionServices.myQuestionList(request),5);
        model.addAttribute("pageInfo", info);
        return "profile";
    }
}
