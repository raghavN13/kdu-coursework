package com.kdu.smarthome.mappers;

import com.kdu.smarthome.dto.Inventory.InventoryRequestDTO;
import com.kdu.smarthome.entities.Inventory;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Component;
/**
 * Maps the DTO to the object
 */
@Component
public class InventoryMapper {

    public Inventory inventoryMapper(InventoryRequestDTO inventoryRequestDTO){
        Inventory inventory = new Inventory();
        inventory.setKickstonId(inventoryRequestDTO.getKickstonId());
        inventory.setDeviceUsername(inventoryRequestDTO.getDeviceUsername());
        inventory.setDevicePassword(inventoryRequestDTO.getDevicePassword());
        inventory.setManufactureDateTime(inventoryRequestDTO.getManufactureDateTime());
        inventory.setManufactureFactoryPlace(inventoryRequestDTO.getManufactureFactoryPlace());


        return inventory;
    }
}
