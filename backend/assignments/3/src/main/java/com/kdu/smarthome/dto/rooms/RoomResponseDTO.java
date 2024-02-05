package com.kdu.smarthome.dto.rooms;

import com.kdu.smarthome.entities.Rooms;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class RoomResponseDTO {
    private Rooms room;
    private HttpStatus httpStatus;
}
