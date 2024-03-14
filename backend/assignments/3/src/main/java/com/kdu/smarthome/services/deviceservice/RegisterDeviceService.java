package com.kdu.smarthome.services.deviceservice;
import com.kdu.smarthome.Repositories.DeviceRepository;
import com.kdu.smarthome.Repositories.HouseRepository;
import com.kdu.smarthome.Repositories.InventoryRepository;
import com.kdu.smarthome.dto.GeneralDTO.GeneralDTO;
import com.kdu.smarthome.dto.device.DeviceRegisterDTO;
import com.kdu.smarthome.entities.Devices;
import com.kdu.smarthome.entities.Inventory;
import com.kdu.smarthome.exceptions.InvalidCredentialsException;
import com.kdu.smarthome.exceptions.ResourceNotFoundException;
import com.kdu.smarthome.mappers.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterDeviceService {
    private DeviceMapper deviceMapper;
    private DeviceRepository deviceRepository;
    private InventoryRepository inventoryRepository;

    @Autowired
    public RegisterDeviceService(DeviceRepository deviceRepository, DeviceMapper deviceMapper, HouseRepository houseRepository, InventoryRepository inventoryRepository) {
        this.deviceMapper = deviceMapper;
        this.inventoryRepository = inventoryRepository;
        this.deviceRepository = deviceRepository;
    }

    /**
     * Registers the Device by validating it against the inventory
     * @param deviceRegisterDTO
     * @return
     */

    public GeneralDTO registerDevice(DeviceRegisterDTO deviceRegisterDTO){
        Optional<Inventory> inventoryList = inventoryRepository.findByKickstonId(deviceRegisterDTO.getKickstonId());
        Inventory inventory = null;

        if (inventoryList.isEmpty()) {
            throw new ResourceNotFoundException("The Device is not Present in the Inventory");
        } else{

            if(!deviceRegisterDTO.getDeviceUsername().equals(inventoryList.get().getDeviceUsername())){
                throw new ResourceNotFoundException("The device is not valid");
            }
            inventory = inventoryList.get();


            if (inventory.getDevicePassword().equals(deviceRegisterDTO.getDevicePassword())) {
                Devices device = deviceMapper.deviceMapper(deviceRegisterDTO);

                deviceRepository.save(device);
                return new GeneralDTO("", HttpStatus.OK);
            } else {
                throw new InvalidCredentialsException("Incorrect Credentials of the Device");
            }
        }
    }

}
