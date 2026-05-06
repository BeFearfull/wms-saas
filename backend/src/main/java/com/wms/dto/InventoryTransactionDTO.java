package com.wms.dto;

import com.wms.entity.TransactionType;
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
public class InventoryTransactionDTO {
    private UUID id;
    private UUID productId;
    private TransactionType type;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalAmount;
    private BigDecimal stockBefore;
    private BigDecimal stockAfter;
    private String referenceNumber;
    private String notes;
    private LocalDateTime transactionDate;
    private LocalDateTime createdAt;
}
