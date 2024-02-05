package com.kdu.smarthome.config;

import com.kdu.smarthome.Repositories.UserRespository;
import com.kdu.smarthome.entities.Admins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is the the Java Authenticator class that is used for authentication of the Username and the Password
 */

@Component
public class MyAuthenticator implements AuthenticationProvider {
    private UserRespository userRespository;


    @Autowired

    public MyAuthenticator(UserRespository userRespository) {
        this.userRespository = userRespository;

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return grantedAuthorities;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        Optional<Admins>adminList = userRespository.findByUsername(username);
        Admins admin = null;
        if(adminList.isEmpty()){
            throw new BadCredentialsException("No user Exists");
        }
        else{
            admin =  adminList.get();
            if(passwordEncoder().matches(pwd,admin.getPassword())){
                return new UsernamePasswordAuthenticationToken(username,pwd,getGrantedAuthorities(admin.getRole()));
            }
            else{
                throw new BadCredentialsException("The Password is Invalid");
            }
        }

    }
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
