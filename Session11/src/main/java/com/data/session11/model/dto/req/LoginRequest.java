package com.data.session11.model.dto.req;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}