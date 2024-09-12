package com.example.demo.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;
@Data
public class ShiftsDTO {
    UUID shiftTypeId;
    String name;
    Date dateStart;
    Date dateEnd;
    Time timeStart;
    Time timeEnd;
    String timeZone;
    UUID tenantId;
}
