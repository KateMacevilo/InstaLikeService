package com.tms.InstaLike.service;

import com.tms.InstaLike.entity.Like;
import com.tms.InstaLike.entity.Post;
import com.tms.InstaLike.entity.User;

import java.util.List;

public interface PostService {


    Post save (Post post);
    List<Post> getAllUserPost(User user);
    List<Post> getAllPosts();




}
