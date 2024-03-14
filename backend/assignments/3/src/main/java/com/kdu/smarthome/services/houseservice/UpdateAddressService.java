package com.kdu.smarthome.services.houseservice;

import com.kdu.smarthome.Repositories.HouseRepository;
import com.kdu.smarthome.Repositories.UserRespository;
import com.kdu.smarthome.utility.JSONUtility;
import com.kdu.smarthome.utility.TokenDecoder;
import com.kdu.smarthome.dto.GeneralDTO.GeneralDTO;
import com.kdu.smarthome.entities.Houses;
import com.kdu.smarthome.exceptions.ResourceNotFoundException;
import com.kdu.smarthome.mappers.HouseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateAddressService {
    private HouseRepository houseRepository;
    private HouseMapper houseMapper;
    private JSONUtility jsonUtility;
    private UserRespository userRespository;

    private TokenDecoder tokenDecoder;

    public UpdateAddressService(HouseRepository houseRepository, HouseMapper houseMapper, JSONUtility jsonUtility, UserRespository userRespository, TokenDecoder tokenDecoder) {
        this.houseRepository = houseRepository;
        this.houseMapper = houseMapper;
        this.jsonUtility = jsonUtility;
        this.userRespository = userRespository;
        this.tokenDecoder = tokenDecoder;
    }

    /**
     * Updates the address of the house with proper validation
     * @param id
     * @param newAddress
     * @return
     */
    public GeneralDTO updateAddress(Long id, String newAddress){
        Optional<Houses> houseList = houseRepository.findById(id);
        if(houseList.isPresent()){
            Houses house = houseList.get();
            house.setAddress(newAddress);
            houseRepository.save(house);
            return new GeneralDTO("", HttpStatus.OK);
        }
        else{
            throw new ResourceNotFoundException("House not found ");
        }
    }


}
