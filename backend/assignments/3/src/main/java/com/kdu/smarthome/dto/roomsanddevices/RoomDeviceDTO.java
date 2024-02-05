package com.kdu.smarthome.dto.roomsanddevices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDeviceDTO {
    private String message;
    private String roomsDevices;
    private HttpStatus httpStatus;
}
