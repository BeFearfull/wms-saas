package com.wms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "purchases", indexes = {
    @Index(name = "idx_warehouse_id", columnList = "warehouse_id"),
    @Index(name = "idx_supplier_id", columnList = "supplier_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Company supplier;

    @Column(nullable = false, unique = true)
    private String poNumber; // Purchase Order Number

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PurchaseStatus status;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private LocalDateTime expectedDeliveryDate;

    private LocalDateTime actualDeliveryDate;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal gstAmount;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal netAmount;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal paidAmount;

    private String invoiceNumber;
    private LocalDateTime invoiceDate;

    private String transporterName;
    private String transporterPhone;
    private String transporterAddress;
    private String awbNumber; // Air Waybill Number

    private String notes;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PurchaseItem> items;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = PurchaseStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
