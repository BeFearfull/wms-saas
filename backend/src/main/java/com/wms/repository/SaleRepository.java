package com.wms.repository;

import com.wms.entity.Sale;
import com.wms.entity.SaleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {
    List<Sale> findByWarehouseId(UUID warehouseId);
    Optional<Sale> findByIdAndWarehouseId(UUID id, UUID warehouseId);
    Page<Sale> findByWarehouseId(UUID warehouseId, Pageable pageable);
    List<Sale> findByWarehouseIdAndStatus(UUID warehouseId, SaleStatus status);
    List<Sale> findByWarehouseIdAndOrderDateBetween(
        UUID warehouseId, LocalDateTime startDate, LocalDateTime endDate
    );
    Optional<Sale> findBySoNumber(String soNumber);
}
