package com.kdu.smarthome.dto.roomsanddevices;

import com.kdu.smarthome.entities.Devices;
import com.kdu.smarthome.entities.Houses;
import com.kdu.smarthome.entities.Rooms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomsDevicesResponseDTO {
    private Houses house;
    private List<Rooms> rooms;
    private List<List<Devices>>arr;


}
