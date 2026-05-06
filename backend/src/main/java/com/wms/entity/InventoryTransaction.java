package com.wms.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * InventoryTransaction Entity - Tracks all stock movements
 * Critical for audit trail and accurate inventory management
 * Supports +50 (purchase), -10 (sale), -5 (damage), +20 (returned) patterns
 */
@Entity
@Table(name = "inventory_transactions", indexes = {
        @Index(name = "idx_inv_trans_warehouse_id", columnList = "warehouse_id"),
        @Index(name = "idx_inv_trans_product_id", columnList = "product_id"),
        @Index(name = "idx_inv_trans_type", columnList = "transaction_type"),
        @Index(name = "idx_inv_trans_created_at", columnList = "created_at")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long quantityBefore;

    @Column(nullable = false)
    private Long quantityAfter;

    @Column(length = 500)
    private String reason;

    @Column(length = 500)
    private String reference;

    @Column(length = 255)
    private String createdBy;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public enum TransactionType {
        PURCHASE("Purchase"),
        SALE("Sale"),
        RETURN("Return"),
        DAMAGE("Damage"),
        ADJUSTMENT("Adjustment"),
        TRANSFER("Transfer"),
        STOCK_TAKE("Stock Take");

        private final String label;

        TransactionType(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}
