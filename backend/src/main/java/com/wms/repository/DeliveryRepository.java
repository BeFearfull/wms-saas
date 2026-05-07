package com.wms.repository;

import com.wms.entity.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Optional<Delivery> findByDeliveryNumber(String deliveryNumber);
    List<Delivery> findByWarehouseId(Long warehouseId);
    @Query("SELECT d FROM Delivery d WHERE d.warehouse.id = ?1 AND d.deliveryType = ?2 ORDER BY d.expectedDate ASC")
    Page<Delivery> findByWarehouseAndType(Long warehouseId, Delivery.DeliveryType type, Pageable pageable);
    @Query("SELECT d FROM Delivery d WHERE d.warehouse.id = ?1 AND d.status = ?2")
    List<Delivery> findByWarehouseAndStatus(Long warehouseId, Delivery.DeliveryStatus status);
    @Query("SELECT d FROM Delivery d WHERE d.purchase.id = ?1")
    List<Delivery> findByPurchaseId(Long purchaseId);
    @Query("SELECT d FROM Delivery d WHERE d.sale.id = ?1")
    List<Delivery> findBySaleId(Long saleId);
}
