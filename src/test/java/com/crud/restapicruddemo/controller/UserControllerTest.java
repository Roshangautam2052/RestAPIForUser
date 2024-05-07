package com.crud.restapicruddemo.controller;

import com.crud.restapicruddemo.dto.UserDto;
import com.crud.restapicruddemo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;
    private UserDto userDto;
    private final List<UserDto> userDtoList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        userDto = UserDto.builder().userName("Sam Tilly").email("samtilly21@gmail.com").build();
        UserDto userDtoTwo = UserDto.builder().userName("Kevin Hart").email("kevinhart21@gmail.com").build();
        userDtoList.add(userDto);
        userDtoList.add(userDtoTwo);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateUser() throws Exception {
        given(userService.saveUser(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        response.andExpect(status().isCreated());

    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.getAllUser()).thenReturn(userDtoList);
        this.mockMvc.perform(get("/api/user"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testFindUserById() throws Exception {
        Integer userId = 1;
        when(userService.getUserById(userId)).thenReturn(userDto);

        ResultActions response = mockMvc.perform(get("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        response.andExpect(status().isOk());
    }

    @Test
    void testUpdateUser() throws Exception {
        Integer userId = 1;
        when(userService.updateUser(userId, userDto)).thenReturn(userDto);

        ResultActions response = mockMvc.perform(put("/api/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                         .content(objectMapper.writeValueAsString(userDto)));

        response.andExpect(status().isOk());

    }

    @Test
    void testDeleteUser() throws Exception {
        Integer userId = 1;
        doNothing().when(userService).deleteUser(userId);

        ResultActions response = mockMvc.perform(delete("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());

    }
}
