package com.example.app_list_of_lessons.service;

import com.example.app_list_of_lessons.dto.UserDto;
import com.example.app_list_of_lessons.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
