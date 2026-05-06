package com.wms.repository;

import com.wms.entity.Purchase;
import com.wms.entity.PurchaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
    List<Purchase> findByWarehouseId(UUID warehouseId);
    Optional<Purchase> findByIdAndWarehouseId(UUID id, UUID warehouseId);
    Page<Purchase> findByWarehouseId(UUID warehouseId, Pageable pageable);
    List<Purchase> findByWarehouseIdAndStatus(UUID warehouseId, PurchaseStatus status);
    List<Purchase> findByWarehouseIdAndExpectedDeliveryDateBetween(
        UUID warehouseId, LocalDateTime startDate, LocalDateTime endDate
    );
    Optional<Purchase> findByPoNumber(String poNumber);
}
