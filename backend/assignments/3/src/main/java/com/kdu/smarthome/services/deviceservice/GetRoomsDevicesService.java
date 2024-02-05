package com.kdu.smarthome.services.deviceservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.Repositories.HouseRepository;
import com.kdu.smarthome.utility.JSONUtility;
import com.kdu.smarthome.dto.roomsanddevices.RoomsDevicesResponseDTO;
import com.kdu.smarthome.dto.roomsanddevices.RoomDeviceDTO;
import com.kdu.smarthome.entities.Devices;
import com.kdu.smarthome.entities.Houses;
import com.kdu.smarthome.entities.Rooms;
import com.kdu.smarthome.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class GetRoomsDevicesService {
    private HouseRepository houseRepository;
    private JSONUtility jsonUtility;
    @Autowired
    public GetRoomsDevicesService(HouseRepository houseRepository , JSONUtility jsonUtility) {
        this.houseRepository = houseRepository;
        this.jsonUtility = jsonUtility;
    }

    /**
     * Fetches the Rooms and the Devices
     * @param id
     * @return
     * @throws JsonProcessingException
     */
    public RoomDeviceDTO getRoomsAndDevices(Long id) throws JsonProcessingException {
        Optional<Houses> houseList = houseRepository.findById(id);
        if(houseList.isPresent()){
            Houses house = houseList.get();
            List<Rooms>roomList = house.getRooms();
            List<List<Devices>>arr = new ArrayList<>();
            for(Rooms room : roomList){
                List<Devices> devices = room.getDeviceList();
                arr.add(devices);
            }

            RoomsDevicesResponseDTO roomsDevicesResponseDTO = new RoomsDevicesResponseDTO(house,roomList,arr);
            String roomsDevices = jsonUtility.objectConversion(roomsDevicesResponseDTO);

            return new RoomDeviceDTO("Fetched successfully!",roomsDevices,HttpStatus.OK);        }
        else{
            throw new ResourceNotFoundException("The House is Not Found");
        }
    }
}
