package com.paulina.backenduserservice.service;

import com.paulina.backenduserservice.shared.dtos.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService {
    public UserDto createUser(UserDto userDto);
}
