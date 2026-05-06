package com.wms.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Product Entity - Represents items stored in warehouse
 * Each product belongs to a company/supplier
 */
@Entity
@Table(name = "products", indexes = {
        @Index(name = "idx_product_company_id", columnList = "company_id"),
        @Index(name = "idx_product_sku", columnList = "sku")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String sku;

    @Column(length = 100)
    private String category;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal purchasePrice;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sellingPrice;

    @Column(nullable = false, length = 50)
    private String unitType; // kg, bag, piece, carton, etc.

    @Column(nullable = false)
    @Builder.Default
    private Long currentStock = 0L;

    @Column(nullable = false)
    @Builder.Default
    private Long minStockLevel = 10L;

    @Column(nullable = false)
    @Builder.Default
    private Long maxStockLevel = 1000L;

    @Column(nullable = false)
    @Builder.Default
    private Double reorderPoint = 50.0;

    @Column(length = 255)
    private String barcode;

    @Column(length = 500)
    private String imageUrl;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<InventoryTransaction> inventoryTransactions = new HashSet<>();
}
