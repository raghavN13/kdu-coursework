package com.kdu.smarthome.utility;

import com.kdu.smarthome.config.MyAuthenticator;
import com.kdu.smarthome.dto.Users.AdminRequestDTO;
import com.kdu.smarthome.filters.TokenGeneratorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    private MyAuthenticator myAuthenticator;
    private TokenGeneratorFilter tokenGeneratorFilter;
    @Autowired
    public TokenGenerator(MyAuthenticator myAuthenticator, TokenGeneratorFilter tokenGeneratorFilter) {
        this.myAuthenticator = myAuthenticator;
        this.tokenGeneratorFilter = tokenGeneratorFilter;
    }

    public String generateToken(AdminRequestDTO adminRequestDTO){
        Authentication authentication = myAuthenticator.authenticate(
                new UsernamePasswordAuthenticationToken(adminRequestDTO.getUsername(), adminRequestDTO.getPassword()));

        return tokenGeneratorFilter.generateJWT(authentication);
    }


}
