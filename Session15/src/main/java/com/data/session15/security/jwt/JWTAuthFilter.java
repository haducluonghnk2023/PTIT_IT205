package com.data.session15.security.jwt;

import com.data.session15.security.principal.CustomUserDetailServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private CustomUserDetailServices customUserDetailServices;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();

        // ✅ Bỏ qua filter cho các endpoint không yêu cầu xác thực
        if (path.startsWith("/api/v1/auth/register") || path.startsWith("/api/v1/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = getTokenFromUser(request);
            if (token != null && jwtProvider.validateToken(token)) {
                String username = jwtProvider.getUsernameFromToken(token);
                UserDetails userDetails = customUserDetailServices.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("Không thể xác thực JWT: {}", e.getMessage());

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Map<String, Object> error = new HashMap<>();
            error.put("error", "Unauthorized");
            error.put("message", e.getMessage());

            new ObjectMapper().writeValue(response.getOutputStream(), error);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromUser(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }
}
