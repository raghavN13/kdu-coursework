package com.kdu.smarthome.mappers;

import com.kdu.smarthome.dto.house.HouseRequestDTO;
import com.kdu.smarthome.entities.Admins;
import com.kdu.smarthome.entities.Houses;
import org.springframework.stereotype.Component;
/**
 * Maps the DTO to the object
 */
@Component
public class HouseMapper {

    public Houses mapHouse(HouseRequestDTO houseRequestDTO, Admins admin){
        Houses house = new Houses();
        house.setAddress(houseRequestDTO.getAddress());
        house.setHouseName(houseRequestDTO.getHouseName());
        house.getUsersList().add(admin);
        return house;
    }
}
