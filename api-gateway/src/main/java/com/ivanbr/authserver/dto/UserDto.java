package com.ivanbr.authserver.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
