package com.xcy.community2.controller;

import com.xcy.community2.dto.QuestionDTO;
import com.xcy.community2.service.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateController {

    @Autowired
    private QuestionServices questionServices;

    @GetMapping("/updatequestion/{id}")
    public String publish(@PathVariable(name = "id")Integer id,
                          Model model){
        QuestionDTO questionDTO = questionServices.getQuestionDTOById(id);
        model.addAttribute("id",id);
        model.addAttribute("question",questionDTO);
        return "updateQuestion";
    }
    @PostMapping("/updatequestion")
    public String QuestionEdit(@RequestParam(name = "id")Integer id,
                               @RequestParam(name = "title")String title,
                               @RequestParam(name = "description")String description,
                               @RequestParam(name = "tag")String tag,
                               Model model){

        QuestionDTO questionDTO = questionServices.updateQuestion(title,description,tag,id);
        model.addAttribute("id",id);
        model.addAttribute("question",questionDTO);
        return "updateQuestion";
    }
}
