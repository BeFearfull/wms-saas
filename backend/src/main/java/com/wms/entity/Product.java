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
@Table(name = "products", indexes = {
    @Index(name = "idx_warehouse_id", columnList = "warehouse_id"),
    @Index(name = "idx_company_id", columnList = "company_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private String name;

    private String description;
    private String sku;
    private String barcode;

    @Column(nullable = false)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitType unit;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal pricePerUnit;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal currentStock;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal minimumStockLevel;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal maximumStockLevel;

    private String imageUrl;

    private String hsn;
    private BigDecimal gstRate;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InventoryTransaction> transactions;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        active = true;
        if (currentStock == null) {
            currentStock = BigDecimal.ZERO;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
