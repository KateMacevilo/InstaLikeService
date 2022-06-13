package com.tms.InstaLike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String start(Model model){
        return "/index";
    }


    @GetMapping("/myPage")
    public String showMyPage(){
        return "mainPage";
    }


    @GetMapping("/posts")
    public String showPosts(){
        return "posts";
    }






}
