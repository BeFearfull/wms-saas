package com.wms.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
    private Long id;
    private String invoiceNumber;
    private Long customerId;
    private String customerName;
    private Double totalQuantity;
    private Double totalAmountINR;
    private String status;
    private LocalDate saleDate;
    private LocalDate deliveryDate;
    private Double shippedQuantity;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
