package com.tms.InstaLike.service;

import com.tms.InstaLike.entity.User;
import com.tms.InstaLike.web.dto.UserRegistrationDto;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
}
