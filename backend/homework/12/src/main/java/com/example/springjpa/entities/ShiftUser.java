package com.example.springjpa.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Entity
@Table(name="shift_users")
@Data
public class ShiftUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="shift_id")
    private Shifts shift;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="tenant_id")
    private Tenant tenant;
}
