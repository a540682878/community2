package com.xcy.community2.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.deploy.net.HttpResponse;
import com.xcy.community2.mapper.UserMapper;
import com.xcy.community2.service.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionServices questionServices;

    //首页
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1") Integer pn,
                        @RequestParam(defaultValue = "5") Integer pageSize,
                        Model model) {


        //PN:(pageNum)的缩写，表示当前的页数；pageSize表示每页显示的数量
        Page p = PageHelper.startPage(pn, pageSize);
        //调用questionServices.list()方法,得到List<Question>
        //PageInfo<Question>内集合了所有的question信息即分页功能
        //navigatePages代表有有5个分页导航按钮
        PageInfo info = new PageInfo<>(questionServices.list(), 5);
        model.addAttribute("pageInfo", info);
        return "index";
    }

    //退出登录
    @GetMapping("/outlogin")
    public String outLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        //删除session
        session.removeAttribute("user");
        //删除cookie
        Cookie cookies[] = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                //删除
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                break;
            }
        }
        return "redirect:/";
    }
}