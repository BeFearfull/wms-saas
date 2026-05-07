package com.wms.repository;

import com.wms.entity.InventoryTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {
    List<InventoryTransaction> findByProductId(Long productId);
    List<InventoryTransaction> findByWarehouseId(Long warehouseId);
    @Query("SELECT it FROM InventoryTransaction it WHERE it.warehouse.id = ?1 ORDER BY it.transactionDate DESC")
    Page<InventoryTransaction> findByWarehouseIdOrderByDateDesc(Long warehouseId, Pageable pageable);
    @Query("SELECT it FROM InventoryTransaction it WHERE it.product.id = ?1 ORDER BY it.transactionDate DESC")
    List<InventoryTransaction> findByProductIdOrderByDateDesc(Long productId);
    @Query("SELECT it FROM InventoryTransaction it WHERE it.warehouse.id = ?1 AND it.transactionDate BETWEEN ?2 AND ?3")
    List<InventoryTransaction> findByWarehouseAndDateRange(Long warehouseId, LocalDateTime startDate, LocalDateTime endDate);
}
