package com.xcy.community2.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xcy.community2.dto.QuestionDTO;
import com.xcy.community2.model.User;
import com.xcy.community2.mapper.UserMapper;
import com.xcy.community2.service.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionServices questionServices;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1")Integer pageNum,
                        @RequestParam(defaultValue = "2")Integer pageSize,
                        Model model) {


            //pageNum表示当前的页数；pageSize表示每页显示的数量
            Page p = PageHelper.startPage(pageNum,pageSize);
            //调用questionServices.questionPage()方法,得到List<QuestionDTO>
            //PageInfo<Question>内集合了所有的question信息即分页功能
            PageInfo info = new PageInfo<>(questionServices.questionPage());
            model.addAttribute("pageInfo", info);
            return "index";
        }
    }

