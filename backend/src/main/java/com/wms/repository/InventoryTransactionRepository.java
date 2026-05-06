package com.wms.repository;

import com.wms.entity.InventoryTransaction;
import com.wms.entity.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, UUID> {
    List<InventoryTransaction> findByWarehouseId(UUID warehouseId);
    List<InventoryTransaction> findByProductId(UUID productId);
    List<InventoryTransaction> findByWarehouseIdAndProductId(UUID warehouseId, UUID productId);
    Page<InventoryTransaction> findByWarehouseId(UUID warehouseId, Pageable pageable);
    List<InventoryTransaction> findByProductIdAndType(UUID productId, TransactionType type);
    List<InventoryTransaction> findByWarehouseIdAndTransactionDateBetween(
        UUID warehouseId, LocalDateTime startDate, LocalDateTime endDate
    );
}
