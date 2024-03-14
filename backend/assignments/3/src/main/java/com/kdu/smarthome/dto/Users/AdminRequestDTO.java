package com.kdu.smarthome.dto.Users;

import lombok.Data;

@Data
public class AdminRequestDTO {
    private String username;

    private String password;

    private String name;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

}
