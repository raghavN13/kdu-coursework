package com.example.demo.entities;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class User {
    private UUID userId;
    private String username;
    private int  loggedin;
    private String time_zone;
    private UUID tenant_id;
    private Time createTime;
    private Time updateTime;
}
