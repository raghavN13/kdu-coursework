package com.kdu.smarthome.Repositories;

import com.kdu.smarthome.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Rooms,Long> {
}
