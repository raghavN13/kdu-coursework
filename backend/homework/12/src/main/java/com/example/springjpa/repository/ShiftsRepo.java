package com.example.springjpa.repository;
import com.example.springjpa.entities.Shifts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ShiftsRepo extends JpaRepository<Shifts,UUID> {

}
