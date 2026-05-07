package com.wms.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "purchases", indexes = {
    @Index(name = "idx_purchase_warehouse", columnList = "warehouse_id"),
    @Index(name = "idx_purchase_supplier", columnList = "supplier_id"),
    @Index(name = "idx_purchase_status", columnList = "status")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Company supplier;

    @Column(name = "po_number", nullable = false, unique = true)
    private String poNumber; // Purchase Order Number

    @Column(name = "total_quantity", nullable = false)
    private Double totalQuantity;

    @Column(name = "total_amount_inr", nullable = false)
    private Double totalAmountINR; // In Indian Rupees

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status = PurchaseStatus.PENDING;

    @Column(name = "expected_delivery_date")
    private LocalDate expectedDeliveryDate;

    @Column(name = "actual_delivery_date")
    private LocalDate actualDeliveryDate;

    @Column(name = "received_quantity")
    private Double receivedQuantity = 0.0;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_by")
    private Long createdBy; // User ID

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Delivery> deliveries = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum PurchaseStatus {
        PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED, PARTIAL
    }
}
