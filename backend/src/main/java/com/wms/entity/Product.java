package com.wms.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products", indexes = {
    @Index(name = "idx_product_warehouse", columnList = "warehouse_id"),
    @Index(name = "idx_product_company", columnList = "company_id"),
    @Index(name = "idx_product_name", columnList = "name")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private String name;

    @Column(name = "product_category", nullable = false)
    private String category;

    @Column(name = "price_inr", nullable = false)
    private Double priceInINR; // Price in Indian Rupees

    @Column(name = "unit_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Column(name = "current_stock", nullable = false)
    private Double currentStock = 0.0;

    @Column(name = "minimum_stock_level")
    private Double minimumStockLevel = 0.0;

    @Column(name = "maximum_stock_level")
    private Double maximumStockLevel;

    @Column(name = "reorder_quantity")
    private Double reorderQuantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "sku", unique = true)
    private String sku;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InventoryTransaction> transactions = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum UnitType {
        KG, GRAM, LITER, MILLILITER, PIECE, BAG, CARTON, BOX, DOZEN, BOTTLE, CAN, JAR, PACKET
    }
}
