package com.kdu.smarthome.controllers;

import com.kdu.smarthome.dto.GeneralDTO.GeneralDTO;
import com.kdu.smarthome.dto.device.DeviceAddRequestDTO;
import com.kdu.smarthome.dto.device.DeviceRegisterDTO;
import com.kdu.smarthome.services.deviceservice.AddDeviceService;
import com.kdu.smarthome.services.deviceservice.RegisterDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is the Device Controller and it is used to hit the endpoints for the device addition and other device related queries
 */
@RestController
@RequestMapping("/api/v1")
public class DeviceController {
    private AddDeviceService addDeviceService;
    private RegisterDeviceService registerDeviceService;
    @Autowired
    public DeviceController(AddDeviceService addDeviceService, RegisterDeviceService registerDeviceService) {
        this.addDeviceService = addDeviceService;
        this.registerDeviceService = registerDeviceService;
    }

    /**
     * This is used to register the device
     * @param deviceRegisterDTO
     * @return
     */
    @PostMapping("/device/register")
    public ResponseEntity<GeneralDTO> register(@RequestBody DeviceRegisterDTO deviceRegisterDTO){
        GeneralDTO generalDTO = registerDeviceService.registerDevice(deviceRegisterDTO);
        return new ResponseEntity<>(generalDTO, HttpStatus.OK);
    }

    /**
     * Used to add the device
     * @param deviceAddRequestDTO
     * @return
     */
    @PostMapping("/device/add")
    public ResponseEntity<GeneralDTO> add(@RequestBody DeviceAddRequestDTO deviceAddRequestDTO) {
        if (!isConvertibleToLong(deviceAddRequestDTO.getRoomId()) || !isConvertibleToLong(deviceAddRequestDTO.getHouseId())) {
            System.out.println("entering here ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        System.out.println("But Surely here");

        GeneralDTO generalDTO = addDeviceService.addDevice(deviceAddRequestDTO);
        return new ResponseEntity<>(generalDTO, HttpStatus.OK);
    }

    private boolean isConvertibleToLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
