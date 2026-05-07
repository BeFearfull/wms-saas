package com.wms.repository;

import com.wms.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Optional<Purchase> findByPoNumber(String poNumber);
    List<Purchase> findByWarehouseId(Long warehouseId);
    List<Purchase> findBySupplierId(Long supplierId);
    @Query("SELECT p FROM Purchase p WHERE p.warehouse.id = ?1 ORDER BY p.purchaseDate DESC")
    Page<Purchase> findByWarehouseIdOrderByDateDesc(Long warehouseId, Pageable pageable);
    @Query("SELECT p FROM Purchase p WHERE p.warehouse.id = ?1 AND p.status = ?2")
    List<Purchase> findByWarehouseAndStatus(Long warehouseId, Purchase.PurchaseStatus status);
}
