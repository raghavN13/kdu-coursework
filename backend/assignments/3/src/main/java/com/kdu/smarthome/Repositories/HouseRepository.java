package com.kdu.smarthome.Repositories;


import com.kdu.smarthome.entities.Houses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository

public interface HouseRepository extends JpaRepository<Houses,Long> {
}
