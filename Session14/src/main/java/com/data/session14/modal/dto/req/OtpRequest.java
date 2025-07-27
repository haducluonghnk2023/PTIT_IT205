package com.data.session14.modal.dto.req;

import lombok.Data;

@Data
public class OtpRequest {
    private String username;
    private String password;
    private String otp;
}
