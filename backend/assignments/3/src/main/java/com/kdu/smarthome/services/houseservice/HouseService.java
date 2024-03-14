package com.kdu.smarthome.services.houseservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.Repositories.HouseRepository;
import com.kdu.smarthome.Repositories.UserRespository;
import com.kdu.smarthome.utility.JSONUtility;
import com.kdu.smarthome.utility.TokenDecoder;
import com.kdu.smarthome.dto.house.HouseListDTO;
import com.kdu.smarthome.dto.house.HouseRequestDTO;
import com.kdu.smarthome.dto.house.HouseResponseDTO;
import com.kdu.smarthome.entities.Admins;
import com.kdu.smarthome.entities.Houses;
import com.kdu.smarthome.exceptions.ResourceNotFoundException;
import com.kdu.smarthome.mappers.HouseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {
    private HouseRepository houseRepository;
    private HouseMapper houseMapper;
    private JSONUtility jsonUtility;
    private UserRespository userRespository;

    private TokenDecoder tokenDecoder;

    public HouseService(HouseRepository houseRepository, HouseMapper houseMapper, JSONUtility jsonUtility, UserRespository userRespository, TokenDecoder tokenDecoder) {
        this.houseRepository = houseRepository;
        this.houseMapper = houseMapper;
        this.jsonUtility = jsonUtility;
        this.userRespository = userRespository;
        this.tokenDecoder = tokenDecoder;
    }


    public HouseResponseDTO addHouse(HouseRequestDTO houseRequestDTO, String token){
        String username = tokenDecoder.decodeTokenToGetUsername(token);
        Optional<Admins>adminList = userRespository.findByUsername(username);

        Admins admin = null;
        if(adminList.isEmpty()){
            throw new ResourceNotFoundException("The USer is Invalid");

        }
        else{
            admin = adminList.get();
            admin.setRole("ROLE_ADMIN");

            Houses house = houseMapper.mapHouse(houseRequestDTO,admin);
            houseRepository.save(house);
            userRespository.save(admin);
            return new HouseResponseDTO(house , HttpStatus.OK);
        }
    }

    /**
     * Get the list of all the houses
     * @return
     * @throws JsonProcessingException
     */
    public HouseListDTO getAllHouses() throws JsonProcessingException {
        List<Houses> houseList = houseRepository.findAll();
        String houses = jsonUtility.ListConversion(houseList);
        return new HouseListDTO(houses,"House fetched successfully",HttpStatus.OK);
    }

}
