package com.example.demo.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class ShiftTypeDTO {
    private String shiftName;
    private String description;
    private boolean active;
    private String timezone;
    private UUID tenantId;
}
