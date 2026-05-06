package com.wms.repository;

import com.wms.entity.Company;
import com.wms.entity.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    List<Company> findByWarehouseId(UUID warehouseId);
    List<Company> findByWarehouseIdAndActiveTrue(UUID warehouseId);
    List<Company> findByWarehouseIdAndType(UUID warehouseId, CompanyType type);
    Optional<Company> findByIdAndWarehouseId(UUID id, UUID warehouseId);
    Boolean existsByNameAndWarehouseId(String name, UUID warehouseId);
}
