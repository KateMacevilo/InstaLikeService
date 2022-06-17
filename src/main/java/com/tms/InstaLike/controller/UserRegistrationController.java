package com.tms.InstaLike.controller;

import com.tms.InstaLike.service.UserService;
import com.tms.InstaLike.service.UserServiceImpl;
import com.tms.InstaLike.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistration(){
        return "/registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto){

        if (!userServiceImpl.isExistsUserByEmailAndUserName(registrationDto.getUsername(), registrationDto.getEmail())) {
            userService.save(registrationDto);
            return  "redirect:/registration?success";
        }

        return "redirect:/registration";
    }



}
