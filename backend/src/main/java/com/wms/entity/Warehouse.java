package com.wms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "warehouses", indexes = {
    @Index(name = "idx_warehouse_user", columnList = "user_id"),
    @Index(name = "idx_warehouse_code", columnList = "warehouse_code")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "warehouse_code", unique = true, nullable = false)
    private String warehouseCode;
    
    @Column(nullable = false)
    private String location;
    
    @Column(name = "warehouse_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WarehouseType type;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "manager_name")
    private String managerName;
    
    @Column(name = "manager_phone")
    private String managerPhone;
    
    @Column(name = "manager_email")
    private String managerEmail;
    
    @Column(name = "total_capacity")
    private Double totalCapacity;
    
    @Column(name = "capacity_unit")
    private String capacityUnit;
    
    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;
    
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Company> companies = new HashSet<>();
    
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<InventoryTransaction> transactions = new HashSet<>();
    
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<PurchaseOrder> purchaseOrders = new HashSet<>();
    
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<SalesOrder> salesOrders = new HashSet<>();
    
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Delivery> deliveries = new HashSet<>();
    
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
    
    public enum WarehouseType {
        CATTLE_FEED("Cattle Feed"),
        GROCERY("Grocery"),
        ELECTRONICS("Electronics"),
        MEDICAL("Medical"),
        TEXTILE("Textile"),
        FMCG("FMCG"),
        GENERAL("General");
        
        private final String displayName;
        
        WarehouseType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}
