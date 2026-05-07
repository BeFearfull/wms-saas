package com.wms.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDTO {
    private Long id;
    private String poNumber;
    private Long supplierId;
    private String supplierName;
    private Double totalQuantity;
    private Double totalAmountINR;
    private String status;
    private LocalDate expectedDeliveryDate;
    private LocalDate actualDeliveryDate;
    private Double receivedQuantity;
    private LocalDate purchaseDate;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
