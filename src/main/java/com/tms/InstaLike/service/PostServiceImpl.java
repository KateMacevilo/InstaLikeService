package com.tms.InstaLike.service;

import com.tms.InstaLike.entity.Like;
import com.tms.InstaLike.entity.Post;
import com.tms.InstaLike.entity.User;
import com.tms.InstaLike.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    public List<Post> getAllUserPost(User user) {
        return postRepository.getAllByUser(user);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }


    public Optional<Post> getPostOById(Long id) {
        return postRepository.findById(id);
    }

    public Post getPostById(Long id){
        return postRepository.getPostById(id);
    }
}
