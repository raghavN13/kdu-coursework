package com.kdu.smarthome.mappers;

import com.kdu.smarthome.entities.Houses;
import com.kdu.smarthome.entities.Rooms;
import org.springframework.stereotype.Component;
/**
 * Maps the DTO to the object
 */
@Component
public class RoomMapper {

    public Rooms roomMapper(Houses house , String roomName){
        Rooms room = new Rooms();
        room.setRoomName(roomName);
        room.setHouse(house);
        return room;
    }
}
