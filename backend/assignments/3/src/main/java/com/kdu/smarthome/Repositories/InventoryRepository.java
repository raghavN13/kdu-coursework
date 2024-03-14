package com.kdu.smarthome.Repositories;

import com.kdu.smarthome.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory,String> {

    @Query
    Optional<Inventory> findByKickstonId(String kickstonId);


}
