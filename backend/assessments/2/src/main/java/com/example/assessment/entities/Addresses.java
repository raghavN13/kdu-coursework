package com.example.assessment.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "addresses")
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private UUID addressId;
   private String street;
   private String city;
   private String State;
   @Column(name = "postal_code")
   private String postalCode;

   @ManyToOne
   @JoinColumn(name = "user_name")

    Users user;
}
