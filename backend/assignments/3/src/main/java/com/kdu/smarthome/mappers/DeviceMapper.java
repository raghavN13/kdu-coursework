package com.kdu.smarthome.mappers;

import com.kdu.smarthome.dto.device.DeviceRegisterDTO;
import com.kdu.smarthome.entities.Devices;
import org.springframework.stereotype.Component;
/**
 * Maps the DTO to the object
 */
@Component
public class DeviceMapper {
    public Devices deviceMapper(DeviceRegisterDTO deviceRegisterDTO){
        Devices device = new Devices();

        device.setDeviceUsername(deviceRegisterDTO.getDeviceUsername());
        device.setDevicePassword(deviceRegisterDTO.getDevicePassword());
        device.setKickstonId(deviceRegisterDTO.getKickstonId());
        return device;
    }
}
