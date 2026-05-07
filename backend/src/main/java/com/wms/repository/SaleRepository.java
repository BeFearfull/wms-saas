package com.wms.repository;

import com.wms.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    Optional<Sale> findByInvoiceNumber(String invoiceNumber);
    List<Sale> findByWarehouseId(Long warehouseId);
    List<Sale> findByCustomerId(Long customerId);
    @Query("SELECT s FROM Sale s WHERE s.warehouse.id = ?1 ORDER BY s.saleDate DESC")
    Page<Sale> findByWarehouseIdOrderByDateDesc(Long warehouseId, Pageable pageable);
    @Query("SELECT s FROM Sale s WHERE s.warehouse.id = ?1 AND s.status = ?2")
    List<Sale> findByWarehouseAndStatus(Long warehouseId, Sale.SaleStatus status);
}
