package com.wms.repository;

import com.wms.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByWarehouseId(Long warehouseId);
    Optional<Company> findByIdAndWarehouseId(Long id, Long warehouseId);
    @Query("SELECT c FROM Company c WHERE c.warehouse.id = ?1 AND c.isActive = true ORDER BY c.name ASC")
    List<Company> findActiveByWarehouseId(Long warehouseId);
    Optional<Company> findByGstin(String gstin);
}
