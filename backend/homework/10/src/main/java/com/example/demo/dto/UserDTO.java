package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDTO {
    private String userName;
    private String password;
    private String email;
    private String role;
}
