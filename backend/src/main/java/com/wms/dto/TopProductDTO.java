package com.wms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopProductDTO {
    private String productName;
    private BigDecimal totalSold;
    private BigDecimal totalAmount;
    private Integer transactionCount;
}
