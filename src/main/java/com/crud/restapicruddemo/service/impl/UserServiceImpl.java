package com.crud.restapicruddemo.service.impl;

import com.crud.restapicruddemo.dto.UserDto;
import com.crud.restapicruddemo.exception.UserNotFoundException;
import com.crud.restapicruddemo.mapper.UserMapper;
import com.crud.restapicruddemo.model.UserEntity;
import com.crud.restapicruddemo.repository.UserRepository;
import com.crud.restapicruddemo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        UserEntity userEntity = userRepository.save(UserMapper.mapUserDtoToUser(userDto));
        if(userEntity.getId() != null) {
            return UserMapper.mapToUserDto(userEntity);
        }
        else{
            return null;
        }

    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(UserMapper::mapToUserDto).toList();
    }

    @Override
    public UserDto getUserById(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity != null) {
            return UserMapper.mapToUserDto(userEntity);
        } else {
            return null;
        }

    }

    @Override
    public UserDto updateUser(Integer id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("The user of id " + id + " doesn't exists"));
        userEntity.setUserName(userDto.getUserName());
        userEntity.setEmail(userDto.getEmail());
        return UserMapper.mapToUserDto(userRepository.save(userEntity));

    }

    @Override
    public void deleteUser(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () ->  new UserNotFoundException("The user of id " + id + " doesn't exists"));
        userRepository.delete(userEntity);

    }


}
