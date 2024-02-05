package com.kdu.smarthome.exceptions;

import com.kdu.smarthome.dto.ErrorDTO.ErrorDTO;
import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
    private final transient ErrorDTO errorDTO;

    public ResourceNotFoundException(String message){
        super(message);
        this.errorDTO = new ErrorDTO(message,404);
    }
}
