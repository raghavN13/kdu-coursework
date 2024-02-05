package com.kdu.smarthome.controllers;

import com.kdu.smarthome.dto.rooms.RoomAddDTO;
import com.kdu.smarthome.dto.rooms.RoomResponseDTO;
import com.kdu.smarthome.exceptions.NotAuthorizedException;
import com.kdu.smarthome.services.roomsrevices.AddRoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Gives the Endpoints for the room
 */
@RestController
@RequestMapping("/api/v1")
public class RoomController {

    private AddRoomService addRoomService;
    @Autowired

    public RoomController(AddRoomService addRoomService) {
        this.addRoomService = addRoomService;
    }

    /**
     * adding room to the house
     * @param houseId
     * @param roomAddDTO
     * @param request
     * @return
     */
    @PostMapping("/room")
    public ResponseEntity<RoomResponseDTO>addRoomToHouse(@RequestParam String houseId, @RequestBody RoomAddDTO roomAddDTO, HttpServletRequest request){
        System.out.println("entering here bro ");
        try {
            System.out.println("entering here");
            long parsedRoomId = Long.parseLong(houseId);
            RoomResponseDTO roomResponseDTO = addRoomService.addRoom(parsedRoomId, roomAddDTO.getRoomName());
            return new ResponseEntity<>(roomResponseDTO, HttpStatus.OK);
        } catch (NumberFormatException e) {
            String errorMessage = "Invalid houseId. Please provide a valid number.";
            throw new NotAuthorizedException(errorMessage);
        }
    }
}
