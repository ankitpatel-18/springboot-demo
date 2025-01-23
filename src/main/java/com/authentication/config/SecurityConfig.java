package com.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login", "/logout").permitAll()  // Allow access to login and logout
                        .anyRequest().authenticated()  // Require authentication for all other requests
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")  // Custom login page
                        .defaultSuccessUrl("/", true)  // Redirect to home after successful login
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Default Spring Security logout URL
                        .addLogoutHandler((request, response, authentication) -> {
                            try {
                                // Redirect to Google logout URL
                                response.sendRedirect("https://accounts.google.com/Logout");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .logoutSuccessUrl("/")  // Redirect to home page after logout
                        .permitAll());  // Allow everyone to access logout URL

        return http.build();
    }
}
