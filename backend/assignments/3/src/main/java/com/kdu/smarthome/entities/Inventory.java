package com.kdu.smarthome.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Inventory {
    @Id
    @Column(name = "kickston_id")
    private String kickstonId;

    @Column(name = "device_username")
    private String deviceUsername;

    @Column(name = "device_password")
    private String devicePassword;



    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt = LocalDateTime.of(2024,12,31,23,59);
    @Column(name = "manufacture_date_time")
    private LocalDateTime manufactureDateTime;

    @Column(name = "manufacture_factory_place")
    private String manufactureFactoryPlace;


}
