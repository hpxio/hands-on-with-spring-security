package io.dice28.codelabs.security.rbac.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${app.admin.username}")
    private String adminUserName;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.user.username}")
    private String userUserName;

    @Value("${app.user.password}")
    private String userPassword;

    @Bean
    SecurityFilterChain securityFilterChainConfig(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        requests -> requests.requestMatchers("/api/v1/public/**").permitAll()
                                .requestMatchers("/admin").hasAnyRole("ADMIN")
                                .requestMatchers("/user").hasAnyRole("USER")
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()).build();
    }
    
    @Bean
    UserDetailsService userDetailsServiceConfig() {
        UserDetails adminUser = User.withUsername(adminUserName)
                .password(passwordEncoderConfig().encode(adminPassword))
                .roles("ADMIN", "USER").build();

        UserDetails genericUser = User.withUsername(userUserName)
                .password(passwordEncoderConfig().encode(userPassword))
                .roles("USER").build();

        return new InMemoryUserDetailsManager(adminUser, genericUser);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoderConfig() {
        return new BCryptPasswordEncoder();
    }

}