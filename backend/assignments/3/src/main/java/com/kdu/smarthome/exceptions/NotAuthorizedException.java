package com.kdu.smarthome.exceptions;


import com.kdu.smarthome.dto.ErrorDTO.ErrorDTO;
import lombok.Data;

@Data
public class NotAuthorizedException extends RuntimeException {
    private final transient ErrorDTO errorDTO;

    public NotAuthorizedException(String message){
        super(message);
        this.errorDTO = new ErrorDTO(message,400);
    }

}
