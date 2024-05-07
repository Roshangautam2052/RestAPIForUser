package com.crud.restapicruddemo.mapper;

import com.crud.restapicruddemo.dto.UserDto;
import com.crud.restapicruddemo.model.UserEntity;

public class UserMapper {
    private UserMapper() {

    }

    public static UserEntity mapUserDtoToUser(UserDto userDto) {
        new UserEntity();
        return com.crud.restapicruddemo.model.UserEntity.builder()
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .build();
    }

    public static UserDto mapToUserDto(UserEntity userEntity) {
        new UserDto();
        return UserDto.builder()
                .id(userEntity.getId())
                .userName(userEntity.getUserName())
                .email(userEntity.getEmail())
                .build();
    }
}
