package com.xcy.community2.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xcy.community2.dto.QuestionDTO;
import com.xcy.community2.service.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    QuestionServices questionServices;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "title")String title,
                            @RequestParam(name = "description")String description,
                            @RequestParam(name = "tag")String tag,
                            HttpServletRequest request){
        questionServices.addQuesstion(title,description,tag,request);

        return "publish";
    }



}
