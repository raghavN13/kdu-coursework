package com.example.demo.entities;

import lombok.Data;

import java.security.Timestamp;
import java.sql.Time;
import java.util.UUID;
@Data
public class Tenant {
    private UUID tenantId;
    private String tenantName;
    private Time createTime;
    private Time updateTime;
}
