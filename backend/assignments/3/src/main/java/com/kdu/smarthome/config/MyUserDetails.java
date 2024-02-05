package com.kdu.smarthome.config;
import com.kdu.smarthome.Repositories.UserRespository;
import com.kdu.smarthome.entities.Admins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/**
 * This class helps spring load the usename and the password that is used for the authentication
 */
@Component
public class MyUserDetails implements UserDetailsService {

    private UserRespository userRespository;
    @Autowired

    public MyUserDetails(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    /**
     * Loads the Username from the database
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admins> AdminList = userRespository.findByUsername(username);
        Admins admin = null;
        if(AdminList.isPresent()){
            admin = AdminList.get();
        }

        String personUsername = null;
        String personPassword = null;
        List<GrantedAuthority> authorities = null;

        if(AdminList==null){
            throw new UsernameNotFoundException("Please Enter Valid Username");

        }
        else{
            personUsername = admin.getUsername();
            personPassword = admin.getPassword();

            authorities.add(new SimpleGrantedAuthority(admin.getRole()));
        }

        return new User(personUsername,personPassword,authorities);
    }
}
