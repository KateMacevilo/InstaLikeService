package com.tms.InstaLike.service;

import com.tms.InstaLike.entity.User;
import com.tms.InstaLike.repository.RoleRepository;
import com.tms.InstaLike.repository.UserRepository;
import com.tms.InstaLike.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl() {
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getUsername(),
                passwordEncoder.encode(registrationDto.getPassword()),
                registrationDto.getName(),
                registrationDto.getEmail(),
                roleRepository.findByName("ROLE_USER"));

        return userRepository.save(user);
    }

    public boolean isExistsUserByEmailAndUserName(String userName, String email){
        boolean exists = true;
        Optional<User> user = userRepository.findByUsernameAndEmail(userName, email);

        if (!user.isPresent()){
            exists = false;
        }
        return exists;
    }


}
