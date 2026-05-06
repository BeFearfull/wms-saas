package com.wms.repository;

import com.wms.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByWarehouseId(UUID warehouseId);
    List<Product> findByWarehouseIdAndCompanyId(UUID warehouseId, UUID companyId);
    Optional<Product> findByIdAndWarehouseId(UUID id, UUID warehouseId);
    Page<Product> findByWarehouseIdAndActiveTrue(UUID warehouseId, Pageable pageable);
    List<Product> findByWarehouseIdAndActiveTrueAndCurrentStockLessThanMinimumStockLevel(UUID warehouseId);
}
