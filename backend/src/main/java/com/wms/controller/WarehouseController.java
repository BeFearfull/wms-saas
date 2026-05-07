package com.wms.controller;

import com.wms.dto.WarehouseDTO;
import com.wms.entity.Warehouse;
import com.wms.service.WarehouseService;
import com.wms.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/warehouses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<?> createWarehouse(@CurrentUser Long userId,
                                            @Valid @RequestBody WarehouseDTO warehouseDTO) {
        try {
            Warehouse warehouse = warehouseService.createWarehouse(
                    userId,
                    warehouseDTO.getName(),
                    warehouseDTO.getLocation(),
                    Warehouse.WarehouseType.valueOf(warehouseDTO.getWarehouseType()),
                    warehouseDTO.getCity(),
                    warehouseDTO.getState(),
                    warehouseDTO.getPostalCode(),
                    warehouseDTO.getContactPersonName(),
                    warehouseDTO.getContactPhone(),
                    warehouseDTO.getContactEmail(),
                    warehouseDTO.getTotalCapacity()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(warehouse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Failed to create warehouse: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getWarehouses(@CurrentUser Long userId) {
        try {
            List<WarehouseDTO> warehouses = warehouseService.getActiveWarehousesByUserId(userId)
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(warehouses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Failed to fetch warehouses: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWarehouse(@CurrentUser Long userId, @PathVariable Long id) {
        try {
            Warehouse warehouse = warehouseService.getWarehouseById(id, userId)
                    .orElseThrow(() -> new RuntimeException("Warehouse not found"));
            return ResponseEntity.ok(convertToDTO(warehouse));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWarehouse(@CurrentUser Long userId, @PathVariable Long id,
                                            @Valid @RequestBody WarehouseDTO warehouseDTO) {
        try {
            Warehouse warehouse = warehouseService.updateWarehouse(
                    id, userId,
                    warehouseDTO.getName(),
                    warehouseDTO.getLocation(),
                    warehouseDTO.getCity(),
                    warehouseDTO.getState(),
                    warehouseDTO.getPostalCode(),
                    warehouseDTO.getContactPersonName(),
                    warehouseDTO.getContactPhone(),
                    warehouseDTO.getContactEmail()
            );
            return ResponseEntity.ok(convertToDTO(warehouse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Failed to update warehouse: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWarehouse(@CurrentUser Long userId, @PathVariable Long id) {
        try {
            warehouseService.deleteWarehouse(id, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Failed to delete warehouse: " + e.getMessage()));
        }
    }

    private WarehouseDTO convertToDTO(Warehouse warehouse) {
        return WarehouseDTO.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .location(warehouse.getLocation())
                .warehouseType(warehouse.getWarehouseType().toString())
                .city(warehouse.getCity())
                .state(warehouse.getState())
                .postalCode(warehouse.getPostalCode())
                .country(warehouse.getCountry())
                .contactPersonName(warehouse.getContactPersonName())
                .contactPhone(warehouse.getContactPhone())
                .contactEmail(warehouse.getContactEmail())
                .isActive(warehouse.getIsActive())
                .totalCapacity(warehouse.getTotalCapacity())
                .createdAt(warehouse.getCreatedAt())
                .updatedAt(warehouse.getUpdatedAt())
                .build();
    }
}
