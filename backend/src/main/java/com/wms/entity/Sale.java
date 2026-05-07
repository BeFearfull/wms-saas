package com.wms.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "sales", indexes = {
    @Index(name = "idx_sale_warehouse", columnList = "warehouse_id"),
    @Index(name = "idx_sale_customer", columnList = "customer_id"),
    @Index(name = "idx_sale_status", columnList = "status")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Company customer;

    @Column(name = "invoice_number", nullable = false, unique = true)
    private String invoiceNumber;

    @Column(name = "total_quantity", nullable = false)
    private Double totalQuantity;

    @Column(name = "total_amount_inr", nullable = false)
    private Double totalAmountINR; // In Indian Rupees

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SaleStatus status = SaleStatus.PENDING;

    @Column(name = "sale_date", nullable = false)
    private LocalDate saleDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "shipped_quantity")
    private Double shippedQuantity = 0.0;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_by")
    private Long createdBy; // User ID

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum SaleStatus {
        PENDING, CONFIRMED, DISPATCHED, DELIVERED, CANCELLED, PARTIAL
    }
}
