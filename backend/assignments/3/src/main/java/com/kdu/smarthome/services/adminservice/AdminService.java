package com.kdu.smarthome.services.adminservice;

import com.kdu.smarthome.Repositories.UserRespository;
import com.kdu.smarthome.utility.TokenGenerator;
import com.kdu.smarthome.dto.Users.AdminRequestDTO;
import com.kdu.smarthome.dto.Users.AdminResponseDTO;
import com.kdu.smarthome.entities.Admins;
import com.kdu.smarthome.mappers.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private UserRespository userRespository;
    private AdminMapper adminMapper;
    private TokenGenerator tokenGenerator;

    @Autowired
    public AdminService(UserRespository userRespository, AdminMapper adminMapper, TokenGenerator tokenGenerator) {
        this.userRespository = userRespository;
        this.adminMapper = adminMapper;
        this.tokenGenerator = tokenGenerator;
    }

    /**
     * It is used to register the user
     * @param adminRequestDTO
     * @return
     */
    public AdminResponseDTO registerUser(AdminRequestDTO adminRequestDTO){
        Admins admins = adminMapper.mapUser(adminRequestDTO);
        userRespository.save(admins);
        String token = tokenGenerator.generateToken(adminRequestDTO);
        return new AdminResponseDTO(token,"The User is Registered");
    }
}
