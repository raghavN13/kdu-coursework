package com.caching.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ErrorDTO {
    private String message;
    private int statusCode;
}
