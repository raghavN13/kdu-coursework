package com.kdu.smarthome.services.inventoryservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.Repositories.InventoryRepository;
import com.kdu.smarthome.utility.JSONUtility;
import com.kdu.smarthome.dto.GeneralDTO.GeneralDTO;
import com.kdu.smarthome.dto.Inventory.InventoryRequestDTO;
import com.kdu.smarthome.dto.Inventory.InventoryResponseDTO;
import com.kdu.smarthome.entities.Inventory;
import com.kdu.smarthome.mappers.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;
    private InventoryMapper inventoryMapper;

    private JSONUtility jsonUtility;
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper, JSONUtility jsonUtility) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
        this.jsonUtility = jsonUtility;
    }

    /**
     * Gets the device from the inventory
     * @return
     * @throws JsonProcessingException
     */
    public InventoryResponseDTO getDeviceFromInventory() throws JsonProcessingException {
        List<Inventory> inventories = inventoryRepository.findAll();
        String inventoryString = jsonUtility.ListConversion(inventories);
        return new InventoryResponseDTO(inventoryString, HttpStatus.OK);
    }

    /**
     * adds the device in the inventory
     * @param inventoryRequestDTO
     * @return
     */
    public GeneralDTO addDeviceInInventory(InventoryRequestDTO inventoryRequestDTO){
        Inventory inventory = inventoryMapper.inventoryMapper(inventoryRequestDTO);
        inventoryRepository.save(inventory);
        return new GeneralDTO("", HttpStatus.OK);
    }
}
