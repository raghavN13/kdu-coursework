package com.kdu.smarthome.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JSONUtility {
    private final ObjectMapper objectMapper;
    public JSONUtility() {
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }
    public <T> String objectConversion(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
    public <T> String ListConversion(List<T> objList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(objList);
    }
}

