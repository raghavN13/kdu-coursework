package com.example.springjpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tenants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tenant_id")
    private UUID tenantId;

    @Column(name = "tenant_name")
    private String tenantName;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }
}
