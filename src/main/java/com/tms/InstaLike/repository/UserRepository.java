package com.tms.InstaLike.repository;

import com.tms.InstaLike.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Optional<User> findByUsernameAndEmail(String userName, String email);

}
