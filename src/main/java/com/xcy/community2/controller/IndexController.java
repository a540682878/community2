package com.xcy.community2.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xcy.community2.mapper.UserMapper;
import com.xcy.community2.service.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionServices questionServices;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1")Integer pn,
                        @RequestParam(defaultValue = "5")Integer pageSize,
                        Model model) {


            //PN:(pageNum)的缩写，表示当前的页数；pageSize表示每页显示的数量
            Page p = PageHelper.startPage(pn,pageSize);
            //调用questionServices.list()方法,得到List<Question>
            //PageInfo<Question>内集合了所有的question信息即分页功能
            //navigatePages代表有有5个分页导航按钮
            PageInfo info = new PageInfo<>(questionServices.list(),5);
            model.addAttribute("pageInfo", info);
            return "index";
        }
    }

