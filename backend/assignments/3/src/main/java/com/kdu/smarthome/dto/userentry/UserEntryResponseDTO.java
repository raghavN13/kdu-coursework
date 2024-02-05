package com.kdu.smarthome.dto.userentry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntryResponseDTO {
    private String data;
    private HttpStatus httpStatus;
}
