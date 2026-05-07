package com.wms.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryTransactionDTO {
    private Long id;
    private Long productId;
    private String productName;
    private String transactionType;
    private Double quantity;
    private String unitType;
    private String referenceType;
    private Long referenceId;
    private Double pricePerUnit;
    private Double totalValue;
    private String notes;
    private LocalDateTime transactionDate;
    private LocalDateTime createdAt;
}
