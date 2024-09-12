package com.example.demo.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class ShiftUser {
    private UUID id;
    private UUID shiftId;
    private UUID userId;
    private UUID tenantId;
}
