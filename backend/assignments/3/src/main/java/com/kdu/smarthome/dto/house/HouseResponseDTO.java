package com.kdu.smarthome.dto.house;

import com.kdu.smarthome.entities.Houses;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class HouseResponseDTO {
    private Houses house;
    private HttpStatus httpStatus;
}
