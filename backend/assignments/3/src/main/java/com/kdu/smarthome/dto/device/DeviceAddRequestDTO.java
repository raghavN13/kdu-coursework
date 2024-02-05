package com.kdu.smarthome.dto.device;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceAddRequestDTO {
    private String houseId;
    private String roomId;
    private String kickstonId;
}
