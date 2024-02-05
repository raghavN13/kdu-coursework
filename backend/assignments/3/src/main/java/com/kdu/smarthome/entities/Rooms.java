package com.kdu.smarthome.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")

public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String roomName;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt = LocalDateTime.of(2024,12,31,23,59);

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id")
    @JsonBackReference
    private Houses house;


    @OneToMany(mappedBy = "room")
    @JsonManagedReference
    private List<Devices> deviceList = new ArrayList<>();






}
