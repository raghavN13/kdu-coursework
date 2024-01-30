package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.demo.constants.Constants.ADMIN;
import static com.example.demo.constants.Constants.BASIC;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class CustomSecurityConfig {
    @Bean
    SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/getusers").hasAnyRole(BASIC, ADMIN)
                        .requestMatchers("/createuser").hasRole(ADMIN)
                        .requestMatchers("/getusers/{name}").hasAnyRole(BASIC, ADMIN)
                )
                .csrf().disable()
                .formLogin(withDefaults())
                .httpBasic(withDefaults());

        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
