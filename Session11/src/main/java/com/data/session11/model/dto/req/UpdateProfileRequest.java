package com.data.session11.model.dto.req;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String password;
}
