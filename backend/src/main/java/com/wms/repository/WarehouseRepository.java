package com.wms.repository;

import com.wms.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {
    List<Warehouse> findByOwnerId(UUID ownerId);
    Optional<Warehouse> findByIdAndOwnerId(UUID id, UUID ownerId);
    List<Warehouse> findByOwnerIdAndActiveTrue(UUID ownerId);
}
