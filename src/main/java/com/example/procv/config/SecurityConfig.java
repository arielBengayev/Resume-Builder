package com.example.procv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.antMatchers("/").permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(oauth2Login -> {
                    oauth2Login
                            .loginPage("/")
                            .successHandler((request, response, authentication) -> {
                                response.sendRedirect("/chat");
                            });
                })
                .sessionManagement(session -> session
                        .sessionFixation().migrateSession()
                )
                .csrf(csrf -> csrf.ignoringAntMatchers("/api/**"))
                .build();
    }
}
