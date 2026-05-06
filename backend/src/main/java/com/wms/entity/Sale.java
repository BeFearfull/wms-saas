package com.wms.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Sale Entity - Records sales transactions
 */
@Entity
@Table(name = "sales", indexes = {
        @Index(name = "idx_sale_warehouse_id", columnList = "warehouse_id"),
        @Index(name = "idx_sale_number", columnList = "sale_number")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String saleNumber;

    @Column(nullable = false)
    private LocalDateTime saleDate;

    @Column(nullable = false, length = 255)
    private String customerName;

    @Column(length = 20)
    private String customerPhoneNumber;

    @Column(length = 255)
    private String productName;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Column(precision = 12, scale = 2)
    private BigDecimal discountAmount;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SaleStatus status = SaleStatus.COMPLETED;

    @Column(length = 1000)
    private String notes;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    public enum SaleStatus {
        PENDING,
        COMPLETED,
        CANCELLED,
        RETURNED
    }
}
