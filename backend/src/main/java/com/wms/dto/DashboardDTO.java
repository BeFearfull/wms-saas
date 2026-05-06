package com.wms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDTO {
    private BigDecimal totalInventoryValue;
    private BigDecimal totalSales;
    private BigDecimal totalPurchases;
    private BigDecimal profitLoss;
    private Integer lowStockAlerts;
    private Integer pendingDeliveries;
    private List<MonthlySalesDTO> monthlySalesTrend;
    private List<PurchaseTrendDTO> purchaseTrend;
    private List<TopProductDTO> topSellingProducts;
    private List<CompanySalesDTO> companySales;
}
