package com.tms.InstaLike.controller;

import com.tms.InstaLike.entity.Like;
import com.tms.InstaLike.entity.Post;
import com.tms.InstaLike.entity.User;
import com.tms.InstaLike.repository.UserRepository;
import com.tms.InstaLike.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {

    @Autowired
    private PostService postService;
    private Post post;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/upload")
//    public @ResponseBody String provideUploadInfo(){
//        return "вы можете зашружать фото из того же url";
//    }

    @PostMapping("/upload")
    public @ResponseBody
    String handleFileUpload(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "file", required = false) MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                User user = userRepository.findByUsername(auth.getName());
                post = new Post ( bytes, name, new ArrayList<Like>());
                post.setUser(user);
                postService.save(post);

                return "Успешно загружен файл";
            } catch (Exception e) {
                return "Не удалось загрузить файл " + name;
            }
        }
        return name;
    }
}
