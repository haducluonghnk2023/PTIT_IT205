package com.data.session11.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTResponse {
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private Boolean enabled;
    private String token;

    private Collection<? extends GrantedAuthority> authorities;
}
