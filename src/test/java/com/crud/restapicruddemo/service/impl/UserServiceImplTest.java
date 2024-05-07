package com.crud.restapicruddemo.service.impl;

import com.crud.restapicruddemo.dto.UserDto;
import com.crud.restapicruddemo.model.UserEntity;
import com.crud.restapicruddemo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    AutoCloseable autoCloseable;

    UserDto userDto;
    UserEntity userEntity;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userDto = new UserDto("TestUser", "test@example.com");
        userEntity = new UserEntity(1, "TestUser", "test@example.com");

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testSaveUser() {
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        UserDto savedUser = userServiceImpl.saveUser(userDto);
        assertEquals(userEntity.getId(), savedUser.getId());
        assertEquals(userEntity.getUserName(), savedUser.getUserName());
        assertEquals(userEntity.getEmail(), savedUser.getEmail());

    }

    @Test
    void testGetAllUser() {
        when(userRepository.findAll()).thenReturn(
                new ArrayList<>(Collections.singleton(userEntity))
        );
        assertThat(userServiceImpl.getAllUser().getFirst().getId()).isEqualTo(userEntity.getId());
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(userEntity));
        assertThat(userServiceImpl.getUserById(1).getUserName()).isEqualTo(userEntity.getUserName());
    }

    @Test
    void updateUser() {
        UserDto updatedUserDto = new UserDto("UpdatedUser", "updateduser@example.com");
        when(userRepository.findById(1)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserDto updatedUser = userServiceImpl.updateUser(1, updatedUserDto);
        assertEquals(userEntity.getId(), updatedUser.getId());
        assertEquals(updatedUserDto.getUserName(), updatedUser.getUserName());
        assertEquals(updatedUserDto.getEmail(), updatedUser.getEmail());
    }

    @Test
    void deleteUser() {
        when(userRepository.findById(1)).thenReturn(Optional.of(userEntity));
        userServiceImpl.deleteUser(1);
        verify(userRepository, times(1)).delete(userEntity);
    }
}