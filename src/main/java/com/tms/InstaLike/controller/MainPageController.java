package com.tms.InstaLike.controller;

import com.tms.InstaLike.entity.CurrentUser;
import com.tms.InstaLike.entity.Post;
import com.tms.InstaLike.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String start(Model model){
        String userName = getCurrentUserName();
        model.addAttribute("username", userName );
        return "/index";
    }


    @GetMapping("/mainPage")
    public String showMyPage(Model model){
        model.addAttribute("username", getCurrentUserName());
        model.addAttribute("post", new Post());
        return "mainPage";
    }

    @GetMapping("/posts")
    public String showPosts(){
        return "posts";
    }


    private String getCurrentUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
