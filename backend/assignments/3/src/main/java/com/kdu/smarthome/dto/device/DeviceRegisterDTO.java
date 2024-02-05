package com.kdu.smarthome.dto.device;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceRegisterDTO {
    @JsonProperty("kickston_id")
    private String kickstonId;

    @JsonProperty("device_username")
    private String deviceUsername;

    @JsonProperty("device_password")
    private String devicePassword;
}
