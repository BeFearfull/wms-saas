package com.wms.dto;

import com.wms.entity.UnitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private UUID id;
    private UUID companyId;
    private String name;
    private String description;
    private String sku;
    private String barcode;
    private String category;
    private UnitType unit;
    private BigDecimal pricePerUnit;
    private BigDecimal currentStock;
    private BigDecimal minimumStockLevel;
    private BigDecimal maximumStockLevel;
    private String imageUrl;
    private String hsn;
    private BigDecimal gstRate;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
