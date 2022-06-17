package com.tms.InstaLike.repository;

import com.tms.InstaLike.entity.Like;
import com.tms.InstaLike.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByPostId (Long postId);
    Optional<Like> findByIdAndPostId(Long id, Long postId);
    Optional<Like> findFirstByUserIdAndPostId(Long userId, Long postId);
}
