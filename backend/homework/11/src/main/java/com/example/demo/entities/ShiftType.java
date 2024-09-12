package com.example.demo.entities;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ShiftType {
    private UUID shiftTypeId;
    private String shiftName;
    private String description;
    private boolean active;
    private Time createdAt;
    private Time updatedAt;
    private String timeZone;
    private UUID tenantId;
}
