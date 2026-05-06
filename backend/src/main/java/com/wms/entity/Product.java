package com.wms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products", indexes = {
    @Index(name = "idx_product_company", columnList = "company_id"),
    @Index(name = "idx_product_sku", columnList = "sku")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "sku", unique = true, nullable = false)
    private String sku;
    
    @Column(name = "category", nullable = false)
    private String category;
    
    @Column(name = "price_inr", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceInr;
    
    @Column(name = "unit_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UnitType unitType;
    
    @Column(name = "current_stock", nullable = false)
    @Builder.Default
    private Long currentStock = 0L;
    
    @Column(name = "minimum_stock_level")
    @Builder.Default
    private Long minimumStockLevel = 10L;
    
    @Column(name = "maximum_stock_level")
    private Long maximumStockLevel;
    
    @Column(name = "reorder_quantity")
    private Long reorderQuantity;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<InventoryTransaction> transactions = new HashSet<>();
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<PurchaseOrderItem> purchaseOrderItems = new HashSet<>();
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<SalesOrderItem> salesOrderItems = new HashSet<>();
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
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
    
    public enum UnitType {
        KG("Kilogram"),
        BAG("Bag"),
        PIECE("Piece"),
        CARTON("Carton"),
        LITER("Liter"),
        BOX("Box"),
        METER("Meter"),
        UNIT("Unit"),
        BUNDLE("Bundle");
        
        private final String displayName;
        
        UnitType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}
