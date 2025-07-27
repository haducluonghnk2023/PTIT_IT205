package com.data.session14.modal.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTResponse {
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;
}
