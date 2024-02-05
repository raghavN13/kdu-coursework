package com.kdu.smarthome.dto.house;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseListDTO {

    private String houses;
    private String message;
    private HttpStatus httpStatus;
}
