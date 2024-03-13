package com.nithin.springsecuritypractice.service;

import com.nithin.springsecuritypractice.dto.LoginDto;
import com.nithin.springsecuritypractice.dto.RegisterDto;
import com.nithin.springsecuritypractice.entity.User;

public interface AuthService {
    User register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
