package com.example.demo.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ShiftUserDTO {
    UUID shiftUserId;
    UUID shiftId;
    UUID userId;
    UUID tenantId;
}
