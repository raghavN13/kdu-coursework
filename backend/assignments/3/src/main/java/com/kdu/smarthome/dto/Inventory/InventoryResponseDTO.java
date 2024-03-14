package com.kdu.smarthome.dto.Inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class InventoryResponseDTO {
    private String inventory;
    private HttpStatus httpStatus;
}
