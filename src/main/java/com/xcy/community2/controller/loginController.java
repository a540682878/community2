package com.xcy.community2.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class loginController {
    @GetMapping("/")
    public String login(){
        return "index";
    }
}
