package com.data.session14.sercurity.jwt;
import com.data.session14.repository.TokenBlacklistRepository;
import com.data.session14.sercurity.principal.CustomUserDetailsServices;
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
    private CustomUserDetailsServices customUserDetailsService;

    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository; // ✅

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getTokenFromUser(request);
            if (token != null) {
                // ✅ Check token có bị đưa vào blacklist (đã logout)
                if (tokenBlacklistRepository.existsByToken(token)) {
                    throw new RuntimeException("Token đã bị thu hồi");
                }

                if (jwtProvider.validateToken(token)) {
                    String username = jwtProvider.getUsernameFromToken(token);
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new RuntimeException("Token không hợp lệ hoặc đã hết hạn");
                }
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
            return; // ⛔ Không cho đi tiếp
        }

        filterChain.doFilter(request, response); // ✅ Cho đi tiếp nếu hợp lệ
    }

    private String getTokenFromUser(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }
}
