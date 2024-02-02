package com.example.assessment.DTO;

import lombok.Data;

@Data
public class RegisterAuthDTO {
    String userName;
    String password;
    String role;
    String email;
}
