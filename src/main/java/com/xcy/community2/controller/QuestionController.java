package com.xcy.community2.controller;

import com.xcy.community2.dto.QuestionDTO;
import com.xcy.community2.service.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {

    @Autowired
    private QuestionServices questionServices;

    @GetMapping(value = "/question/{id}")
    public String question(@PathVariable(name = "id")Integer id,
                           Model model){

        QuestionDTO questionDTOById = questionServices.getQuestionDTOById(id);
        model.addAttribute("question",questionDTOById);
        return "question";
    }



}
