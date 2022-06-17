package com.tms.InstaLike.repository;

import com.tms.InstaLike.entity.Like;
import com.tms.InstaLike.entity.Post;
import com.tms.InstaLike.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> getAllByUser(User user);
    Post getPostById(Long id);

}
