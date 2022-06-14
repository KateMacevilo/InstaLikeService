package com.tms.InstaLike.service;

import com.tms.InstaLike.entity.Post;
import com.tms.InstaLike.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.saveAndFlush(post);
    }
}
