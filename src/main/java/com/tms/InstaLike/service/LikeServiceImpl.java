package com.tms.InstaLike.service;

import com.tms.InstaLike.entity.Like;
import com.tms.InstaLike.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public Like save(Like like) {
        return likeRepository.saveAndFlush(like);
    }

    public boolean getLikeByUserIDAndPostId(Long userId, Long postId){

        Optional<Like> like = likeRepository.findFirstByUserIdAndPostId(userId, postId);
        boolean empty = true;

        if (like.isPresent()) {
            empty = false;
        }

        return empty;

    }
}
