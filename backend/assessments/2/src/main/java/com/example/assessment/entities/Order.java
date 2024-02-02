package com.example.assessment.entities;

import lombok.Data;

import java.util.UUID;
@Data
public class Order {

    private UUID orderId;
    private String address;
    private String userName;
}
