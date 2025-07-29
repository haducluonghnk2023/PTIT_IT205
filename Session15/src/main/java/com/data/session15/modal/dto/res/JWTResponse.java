package com.data.session15.modal.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JWTResponse {
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private String token;
    private String address;
    private String fullname;
    private String phone;
    private Collection<? extends GrantedAuthority> authorities;
}
