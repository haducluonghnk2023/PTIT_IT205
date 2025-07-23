package com.data.session11.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegister {
    private String username;
    private String password;
    private String fullname;
    private String address;
    private String email;
    private String phone;
    private List<String> roles;
}
