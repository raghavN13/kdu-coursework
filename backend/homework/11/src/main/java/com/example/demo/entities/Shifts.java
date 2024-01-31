package com.example.demo.entities;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class Shifts {
    private UUID shiftId;
    private UUID shiftTypeId;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Time timeStart;
    private Time timeEnd;
    private Timestamp createdAt;  // Assuming utc time is represented as Timestamp
    private Timestamp updatedAt;  // Assuming utc time is represented as Timestamp
    private String timeZone;
    private UUID tenantId;
}
