package com.kdu.smarthome.dto.rooms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomAddDTO {
    @JsonProperty("room_name")
    private String roomName;
}
