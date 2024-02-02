package com.example.assessment.config;

import com.example.assessment.entities.Users;
import com.example.assessment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UsersDetails implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userService.getUsername(username);
        String userUserName = null;
        String userUserPassword = null;
        List<GrantedAuthority> authorities = null;

        if(user==null){
            throw new UsernameNotFoundException("User Details are Invalid");
        }
        else{
            userUserName = user.getUserName();
            userUserPassword = user.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(userUserName,userUserPassword,authorities);


    }
}

