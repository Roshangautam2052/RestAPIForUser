package com.crud.restapicruddemo.service;

import com.crud.restapicruddemo.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);

    List<UserDto> getAllUser();

    UserDto getUserById(Integer id);

    UserDto updateUser(Integer id, UserDto userDto);

    void deleteUser(Integer id);
}
