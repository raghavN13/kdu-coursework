package com.kdu.smarthome.dto.ErrorDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ErrorDTO implements Serializable {
    String message;
    int statusCode;
}
