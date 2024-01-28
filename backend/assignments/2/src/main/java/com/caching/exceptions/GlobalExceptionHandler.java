package com.caching.exceptions;

import com.caching.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(value = {InvalidRequest.class})
    public ResponseEntity<ErrorDTO>handlleInvalidRequestException(InvalidRequest ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage()+" [Invalid Request] ", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ErrorDTO>allKindsOfException(Exception ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage()+ "[ResourceNotFound]" , HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
