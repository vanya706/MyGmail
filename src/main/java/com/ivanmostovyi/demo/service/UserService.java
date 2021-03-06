package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.dto.UserFormDto;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void create(UserFormDto userFormDto);

    User findByUsername(String username);

}
