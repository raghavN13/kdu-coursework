package com.kdu.smarthome.services.houseservice;
import com.kdu.smarthome.Repositories.HouseRepository;
import com.kdu.smarthome.Repositories.UserRespository;
import com.kdu.smarthome.utility.TokenDecoder;
import com.kdu.smarthome.dto.userentry.UserEntryResponseDTO;
import com.kdu.smarthome.entities.Admins;
import com.kdu.smarthome.entities.Houses;
import com.kdu.smarthome.exceptions.NotAdminException;
import com.kdu.smarthome.exceptions.NotAuthorizedException;
import com.kdu.smarthome.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AddUserService {
    private HouseRepository houseRepository;
    private UserRespository userRespository;

    private TokenDecoder tokenDecoder;

    public AddUserService(HouseRepository houseRepository, UserRespository userRespository, TokenDecoder tokenDecoder) {
        this.houseRepository = houseRepository;
        this.userRespository = userRespository;
        this.tokenDecoder = tokenDecoder;
    }

    /**
     * Adds User to the house and check if the adder is the admin or not
     * @param id
     * @param username
     * @param token
     * @return
     */
    public UserEntryResponseDTO addUserToHouse(Long id, String username, String token){
        String adminUser = tokenDecoder.decodeTokenToGetUsername(token);
        Optional<Admins> adminList = userRespository.findByUsername(adminUser);
        Optional<Admins>userList = userRespository.findByUsername(username);
        if(userList.isEmpty()){
            throw new NotAuthorizedException("The user is not registered");
        }
        Admins admin = null;
        if(adminList.isEmpty()){
            throw new NotAuthorizedException("This use is not present");
        }

        admin = adminList.get();
        if(admin.getRole().equals("ROLE_ADMIN")){
            // get the house
            Optional<Houses> houseList = houseRepository.findById(id);
            if(houseList.isPresent()){
                Houses house = houseList.get();
                userList.ifPresent(admins -> house.getUsersList().add(admins));
                return new UserEntryResponseDTO("", HttpStatus.OK);
            }
            else{
                throw new ResourceNotFoundException("The House is not Found");
            }
        }
        else{
            throw new NotAdminException("The registrer is not admin");
        }

    }
}
