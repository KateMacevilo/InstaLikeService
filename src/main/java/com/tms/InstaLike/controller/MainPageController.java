package com.tms.InstaLike.controller;

import com.tms.InstaLike.entity.Post;
import com.tms.InstaLike.entity.User;
import com.tms.InstaLike.repository.UserRepository;
import com.tms.InstaLike.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String start(Model model) {
        String userName = getCurrentUser().getUsername();
        model.addAttribute("username", userName);
        return "redirect:/mainPage";
    }

    @GetMapping("/mainPage/error")
    public String showError(Model model, String errorMessage) {
        model.addAttribute("error_message", errorMessage);
        return "redirect:/errorPage";
    }


    @GetMapping("/mainPage")
    public String showMyPage(Model model) {
        model.addAttribute("username", getCurrentUser().getUsername());

        List<Post> postList;
        User user = getCurrentUser();
        postList = postService.getAllUserPost(user);
        model.addAttribute("countPosts", postList.size());
        model.addAttribute("postList", postList);

        return "mainPage";
    }

    @GetMapping("/posts")
    public String showPosts() {
        return "posts";
    }


    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        return user;
    }
}
