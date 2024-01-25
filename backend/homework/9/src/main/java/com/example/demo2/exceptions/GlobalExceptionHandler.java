package com.example.demo2.exceptions;

import com.example.demo2.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value={ResourceNotFound.class})
    public ResponseEntity<ErrorDTO>handleResourceNotFoundException(ResourceNotFound ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage()+ "[ResourceNotFound]" , HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ErrorDTO>allKindsOfException(Exception ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage()+ "[ResourceNotFound]" , HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value={InvalidRequest.class})
    public ResponseEntity<ErrorDTO>handleInvalidRequestException(InvalidRequest ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage()+ "[Invalid Request]" , HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
