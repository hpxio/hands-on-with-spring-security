package io.dice28.codelabs.security.jwt.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/h2-console/**")
                                .permitAll().anyRequest().authenticated())
                .csrf(csrf -> csrf.disable()).build();
    }
}
