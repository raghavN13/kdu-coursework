package com.kdu.smarthome.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="devices")
public class Devices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "kickston_id")
    private String kickstonId;
    @Column(name = "device_username")
    private String deviceUsername;

    @Column(name = "device_password")
    private String devicePassword;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @JoinColumn(name = "room_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Rooms room;



}
