package com.kdu.smarthome.config;

import com.kdu.smarthome.filters.TokenGeneratorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * This is the Security Configuration class
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private MyAuthenticator myAuthenticator;

    @Autowired
    public SecurityConfig(MyAuthenticator myAuthenticator) {
        this.myAuthenticator = myAuthenticator;
    }


    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(myAuthenticator);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception {
        http
                .addFilterAfter(new TokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new com.kdu.smarthome.filters.TokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/v1/auth/register").permitAll()
                        .anyRequest().authenticated()).csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(withDefaults());
        return http.build();
    }



    }
