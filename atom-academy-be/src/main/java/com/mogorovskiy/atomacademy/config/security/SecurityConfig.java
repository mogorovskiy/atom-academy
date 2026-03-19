package com.mogorovskiy.atomacademy.config.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    private static final String CONTENT_TYPE = "application/json";
    private static final String UNAUTHORIZED_MESSAGE = "{\"message\": \"Unauthorized: Please login first\"}";
    private static final String FORBIDDEN_MESSAGE = "{\"message\": \"Forbidden: You don't have enough permissions\"}";

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/courses/**").permitAll()
                        .requestMatchers("/api/account/**").hasAnyRole(ADMIN, USER)
                        .requestMatchers("/api/users/**").hasAnyRole(ADMIN)
                        .requestMatchers("/api/admin/**").hasRole(ADMIN)
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptions -> exceptions
                        // Если пользователь вообще не аутентифицирован (нет куки/сессии)
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType(CONTENT_TYPE);
                            response.getWriter().write(UNAUTHORIZED_MESSAGE);
                        })
                        // Если пользователь залогинен, но у него не хватает ролей (Access Denied)
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType(CONTENT_TYPE);
                            response.getWriter().write(FORBIDDEN_MESSAGE);
                        })
                )
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
