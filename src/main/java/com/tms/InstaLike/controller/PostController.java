package com.tms.InstaLike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @PostMapping ("/addPost")
    public String addNewPost(){
        return "";
    }

}
