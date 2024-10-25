package com.multipledb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private String id;
    private String username;
    private String name;
    private String surname;
}
