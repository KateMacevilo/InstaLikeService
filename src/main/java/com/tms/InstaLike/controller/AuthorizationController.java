package com.tms.InstaLike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {


    @GetMapping("/authorization")
    public String authorization() {
        return "/authorization";
    }

    @GetMapping("/authorization-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/authorization";
    }


}
