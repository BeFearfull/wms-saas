package com.wms.repository;

import com.wms.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findByOwnerId(Long ownerId);
    Optional<Warehouse> findByIdAndOwnerId(Long id, Long ownerId);
    @Query("SELECT w FROM Warehouse w WHERE w.owner.id = ?1 AND w.isActive = true")
    List<Warehouse> findActiveByOwnerId(Long ownerId);
}
