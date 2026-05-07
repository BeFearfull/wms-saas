package com.wms.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_transactions", indexes = {
    @Index(name = "idx_transaction_product", columnList = "product_id"),
    @Index(name = "idx_transaction_warehouse", columnList = "warehouse_id"),
    @Index(name = "idx_transaction_type", columnList = "transaction_type"),
    @Index(name = "idx_transaction_date", columnList = "transaction_date")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "quantity", nullable = false)
    private Double quantity; // Positive for inbound, negative for outbound

    @Column(name = "unit_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Product.UnitType unitType;

    @Column(name = "reference_type")
    @Enumerated(EnumType.STRING)
    private ReferenceType referenceType;

    @Column(name = "reference_id")
    private Long referenceId; // ID of Purchase, Sale, or Delivery

    @Column(name = "price_per_unit")
    private Double pricePerUnit;

    @Column(name = "total_value")
    private Double totalValue; // quantity * pricePerUnit

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "created_by")
    private Long createdBy; // User ID who created the transaction

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (transactionDate == null) {
            transactionDate = LocalDateTime.now();
        }
    }

    public enum TransactionType {
        PURCHASE,      // Incoming stock from supplier
        SALE,          // Outgoing stock to customer
        DAMAGE,        // Stock loss due to damage
        RETURN,        // Return from customer
        ADJUSTMENT,    // Manual stock adjustment
        TRANSFER,      // Transfer between warehouses (future)
        SAMPLE,        // Sample given
        THEFT,         // Stock loss due to theft
        EXPIRY         // Stock expired
    }

    public enum ReferenceType {
        PURCHASE, SALE, DELIVERY, ADJUSTMENT
    }
}
