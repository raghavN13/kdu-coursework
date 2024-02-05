package com.kdu.smarthome.exceptions;

import com.kdu.smarthome.dto.ErrorDTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ErrorDTO> handleNotAuthorizedException(NotAuthorizedException ex){
        return new ResponseEntity<>(ex.getErrorDTO(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<>(ex.getErrorDTO(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAdminException.class)
    public ResponseEntity<ErrorDTO> handleUserNotAdminException(NotAdminException ex){
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDTO> handleInvalidCredentialsException(InvalidCredentialsException ex){
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.UNAUTHORIZED);
    }

}
