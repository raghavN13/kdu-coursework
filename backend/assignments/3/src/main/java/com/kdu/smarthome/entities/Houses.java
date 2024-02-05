package com.kdu.smarthome.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="houses")
public class Houses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String houseName;
    private String address;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt = LocalDateTime.of(2024,12,31,23,59);


    @ManyToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Admins> usersList = new ArrayList<>();

    @OneToMany(mappedBy = "house")
    @JsonManagedReference
    private List<Rooms> rooms = new ArrayList<>();


}
