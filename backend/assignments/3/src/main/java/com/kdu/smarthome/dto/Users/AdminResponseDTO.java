package com.kdu.smarthome.dto.Users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminResponseDTO {
   private String token;
   private String message;
}
