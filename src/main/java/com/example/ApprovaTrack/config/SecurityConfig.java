package com.example.departmentbudgetapproval.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/budget-request/**").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic(); // Simple HTTP Basic Auth for demo purposes

        return http.build();
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        var manager = User.builder()
                .username("manager1")
                .password(passwordEncoder().encode("managerpass"))
                .roles("MANAGER")
                .build();

        var user = User.builder()
                .username("user1")
                .password(passwordEncoder().encode("userpass"))
                .roles("USER")
                .build();

        return new org.springframework.security.provisioning.InMemoryUserDetailsManager(manager, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
