package com.his.heal.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration   // 添加这个注解
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtDecoder jwtDecoder) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/test",
                                "/api/v1/test/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api/v1/i18n"
                        ).permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(jwtDecoder))
                        .authenticationEntryPoint((request, response, authException) -> {
                            // 检查请求路径是否在白名单中
                            String path = request.getRequestURI();
                            if (path.startsWith("/api/v1/test") ||
                                    path.startsWith("/v3/api-docs") ||
                                    path.startsWith("/swagger-ui") ||
                                    path.equals("/api/v1/i18n")) {
                                // 对于白名单路径，允许请求通过
                                response.setStatus(HttpServletResponse.SC_OK);
                            } else {
                                // 对于其他路径，返回401未授权
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
                            }
                        })
                )
        ;

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        var jwkSetUri = "http://localhost:44364/.well-known/jwks";
        var decoder = NimbusJwtDecoder
                .withJwkSetUri(jwkSetUri)
                .validateType(false)
                .build();
        return decoder; // ✅ 必须直接返回 decoder，不能用 lambda 包装
    }
}