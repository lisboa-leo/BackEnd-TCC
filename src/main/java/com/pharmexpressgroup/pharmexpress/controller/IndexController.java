package com.pharmexpressgroup.pharmexpress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pharmexpress")
public class IndexController {
    @GetMapping("/home")
    public String Index(){
        return "index";
    }
}
