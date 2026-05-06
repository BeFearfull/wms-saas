package com.wms.repository;

import com.wms.entity.Delivery;
import com.wms.entity.DeliveryStatus;
import com.wms.entity.DeliveryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
    List<Delivery> findByWarehouseId(UUID warehouseId);
    Optional<Delivery> findByIdAndWarehouseId(UUID id, UUID warehouseId);
    Page<Delivery> findByWarehouseId(UUID warehouseId, Pageable pageable);
    List<Delivery> findByWarehouseIdAndType(UUID warehouseId, DeliveryType type);
    List<Delivery> findByWarehouseIdAndStatus(UUID warehouseId, DeliveryStatus status);
    List<Delivery> findByWarehouseIdAndDeliveryDateBetween(
        UUID warehouseId, LocalDateTime startDate, LocalDateTime endDate
    );
}
