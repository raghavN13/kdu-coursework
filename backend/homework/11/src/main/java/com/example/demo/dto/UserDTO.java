package com.example.demo.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class UserDTO {
    String name;
    int loggedIn;
    String timeZone;
    UUID tenantId;
}
