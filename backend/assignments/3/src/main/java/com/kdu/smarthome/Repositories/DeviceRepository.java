package com.kdu.smarthome.Repositories;

import com.kdu.smarthome.entities.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface  DeviceRepository extends JpaRepository<Devices,Long> {

    @Query
    Optional<Devices> findByKickstonId(String kickstonId);
}
