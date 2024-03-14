package com.kdu.smarthome.exceptions;

import com.kdu.smarthome.dto.ErrorDTO.ErrorDTO;
import lombok.Data;

@Data
public class NotAdminException extends RuntimeException {
    private final transient ErrorDTO errorDTO;

    public NotAdminException(String message){
        super(message);
        errorDTO = new ErrorDTO(message,401);
    }
}
