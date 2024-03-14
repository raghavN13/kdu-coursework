package com.kdu.smarthome.services.roomsrevices;

import com.kdu.smarthome.Repositories.HouseRepository;
import com.kdu.smarthome.Repositories.RoomRepository;
import com.kdu.smarthome.dto.rooms.RoomResponseDTO;
import com.kdu.smarthome.entities.Houses;
import com.kdu.smarthome.entities.Rooms;
import com.kdu.smarthome.exceptions.ResourceNotFoundException;
import com.kdu.smarthome.mappers.RoomMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddRoomService {
    private RoomMapper roomMapper;
    private RoomRepository roomRepository;
    private HouseRepository houseRepository;

@Autowired
    public AddRoomService(RoomMapper roomMapper, RoomRepository roomRepository, HouseRepository houseRepository) {
        this.roomMapper = roomMapper;
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
    }

    /**
     * Adds the rooms in the houses
     * @param id
     * @param name
     * @return
     */
    @Transactional
    public RoomResponseDTO addRoom(Long id , String name){
        Optional<Houses>houseList = houseRepository.findById(id);
        Houses house = null;
        if(houseList.isPresent()){
            house = houseList.get();
            Rooms room = roomMapper.roomMapper(house,name);
            roomRepository.save(room);

            return new RoomResponseDTO(room,HttpStatus.OK);
        }
        else{
            throw new ResourceNotFoundException("House does not Exists");
        }
    }
}
