package com.crud.restapicruddemo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String userName;
    private String email;

    public UserDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
