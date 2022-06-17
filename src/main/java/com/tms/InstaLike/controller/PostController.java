package com.tms.InstaLike.controller;

import com.tms.InstaLike.entity.Like;
import com.tms.InstaLike.entity.Post;
import com.tms.InstaLike.entity.User;
import com.tms.InstaLike.repository.PostRepository;
import com.tms.InstaLike.repository.UserRepository;
import com.tms.InstaLike.service.LikeServiceImpl;
import com.tms.InstaLike.service.PostService;
import com.tms.InstaLike.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.*;

@Controller
public class PostController {

    private Post post;

    @Autowired
    PostServiceImpl postService;

    @Autowired
    LikeServiceImpl likeService;

    @Autowired
    private UserRepository userRepository;
    private static List<Post> postList = new ArrayList<>();


    @GetMapping("/otherPosts")
    public String getAllPosts(Model model) {
        postList = postService.getAllPosts();
        model.addAttribute("allPosts", postList);
        return "/posts";
    }


    @PostMapping("/upload")
    public @ResponseBody
    String handleFileUpload(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "file", required = false) MultipartFile file, RedirectAttributes attributes) {
        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();

                User user = getCurrentUser();
                post = new Post(bytes, name);
                post.setUser(user);
                postService.save(post);

                return "redirect:/mainPage";
            } catch (Exception e) {
                attributes.addFlashAttribute("errorMessage", "Не удалось загрузить файл \" + name");
                return "redirect:/mainPage/error";
            }
        }
        return name;
    }

    @GetMapping("/image/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Post> postList)
            throws ServletException, IOException {

        postList = postService.getPostOById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(postList.get().getPhoto());
        response.getOutputStream().close();
    }

    @PostMapping("/otherPosts/addLike/{idPost}")
    public String addLike(@PathVariable("idPost") Long idPost) {
        post = postService.getPostById(idPost);
        boolean likeEmpty = likeService.getLikeByUserIDAndPostId(getCurrentUser().getId(), idPost);

        if (likeEmpty) {
            Like like = new Like(post, getCurrentUser());
            like.setPost(post);
            likeService.save(like);
        }
        return "redirect:/otherPosts";
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        return user;
    }
}
