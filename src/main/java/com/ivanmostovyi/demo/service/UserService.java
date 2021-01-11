package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    void create(UserDto userDto);

    Optional<User> findByUsername(String username);

}
