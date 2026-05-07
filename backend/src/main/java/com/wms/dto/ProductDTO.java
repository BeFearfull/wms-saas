package com.wms.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private Double priceInINR;
    private String unitType;
    private Double currentStock;
    private Double minimumStockLevel;
    private Double maximumStockLevel;
    private Double reorderQuantity;
    private String sku;
    private String barcode;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
