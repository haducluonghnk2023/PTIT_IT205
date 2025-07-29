package com.data.session15.modal.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String userName;
    private String password;
    private String fullName;
    private String address;
    private String email;
    private String phone;
    private boolean enabled;
    private Set<String> roles;
}
