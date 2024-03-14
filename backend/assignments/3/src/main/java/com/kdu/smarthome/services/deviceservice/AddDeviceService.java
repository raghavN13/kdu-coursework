package com.kdu.smarthome.services.deviceservice;

import com.kdu.smarthome.Repositories.DeviceRepository;
import com.kdu.smarthome.Repositories.HouseRepository;
import com.kdu.smarthome.dto.GeneralDTO.GeneralDTO;
import com.kdu.smarthome.dto.device.DeviceAddRequestDTO;
import com.kdu.smarthome.entities.Devices;
import com.kdu.smarthome.entities.Houses;
import com.kdu.smarthome.entities.Rooms;
import com.kdu.smarthome.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddDeviceService {
    private DeviceRepository deviceRepository;
    private HouseRepository houseRepository;

    @Autowired
    public AddDeviceService(DeviceRepository deviceRepository, HouseRepository houseRepository) {
        this.deviceRepository = deviceRepository;
        this.houseRepository = houseRepository;
    }

    /**
     * used to add the device
     * @param deviceAddDTO
     * @return
     */
    public GeneralDTO addDevice(DeviceAddRequestDTO deviceAddDTO) {
        Optional<Devices> deviceList = deviceRepository.findByKickstonId(deviceAddDTO.getKickstonId());
        Optional<Houses> houseList = houseRepository.findById(Long.parseLong(deviceAddDTO.getHouseId()));
        Houses house = null;
        Devices device = null;

        if (deviceList.isEmpty()) {
            throw new ResourceNotFoundException("Not valid");
        }
        if(houseList.isPresent()){
            house = houseList.get();
        }

            device = deviceList.get();


            Long roomId = Long.parseLong(deviceAddDTO.getRoomId());
            Optional<Rooms> optionalRoom = house.getRooms().stream()
                    .filter(room -> room.getId().equals(roomId))
                    .findFirst();

            if (optionalRoom.isPresent()) {
                Rooms room = optionalRoom.get();
                device.setRoom(room);
                deviceRepository.save(device);
                return new GeneralDTO( "", HttpStatus.OK);
            } else {
                throw new ResourceNotFoundException("No Room Exists");
            }


    }
}
