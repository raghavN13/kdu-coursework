package com.kdu.smarthome.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.GeneralDTO.GeneralDTO;
import com.kdu.smarthome.dto.Inventory.InventoryRequestDTO;
import com.kdu.smarthome.dto.Inventory.InventoryResponseDTO;
import com.kdu.smarthome.services.inventoryservice.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This gives the endpoints to access the inventory
 */
@RestController
@RequestMapping("/api/v1")
public class InventoryController {

    private InventoryService inventoryService;
    @Autowired

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory")
    public ResponseEntity<InventoryResponseDTO> getDeviceFromInventory() throws JsonProcessingException {
        InventoryResponseDTO inventoryResponseDTO = inventoryService.getDeviceFromInventory();
        return new ResponseEntity<>(inventoryResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/inventory")
    public ResponseEntity<GeneralDTO> addDevice(@RequestBody InventoryRequestDTO inventoryRequestDTO){
        GeneralDTO generalDTO = inventoryService.addDeviceInInventory(inventoryRequestDTO);
        return new ResponseEntity<>(generalDTO, HttpStatus.OK);
    }
}
