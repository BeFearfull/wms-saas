package com.wms.repository;

import com.wms.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByWarehouseId(Long warehouseId);
    List<Product> findByCompanyId(Long companyId);
    Optional<Product> findByIdAndWarehouseId(Long id, Long warehouseId);
    Optional<Product> findBySku(String sku);
    Optional<Product> findByBarcode(String barcode);
    @Query("SELECT p FROM Product p WHERE p.warehouse.id = ?1 AND p.isActive = true ORDER BY p.name ASC")
    List<Product> findActiveByWarehouseId(Long warehouseId);
    @Query("SELECT p FROM Product p WHERE p.warehouse.id = ?1 AND p.currentStock < p.minimumStockLevel")
    List<Product> findLowStockByWarehouseId(Long warehouseId);
}
