package com.kdu.smarthome.dto.GeneralDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralDTO {
    private String data;
    private HttpStatus httpStatus;
}
