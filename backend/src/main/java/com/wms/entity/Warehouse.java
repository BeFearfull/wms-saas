package com.wms.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Warehouse Entity - Represents a physical warehouse
 * Each warehouse is completely isolated and belongs to one user
 */
@Entity
@Table(name = "warehouses", indexes = {
        @Index(name = "idx_warehouse_user_id", columnList = "user_id"),
        @Index(name = "idx_warehouse_code", columnList = "code")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private WarehouseType type;

    @Column(nullable = false, length = 500)
    private String address;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String state;

    @Column(nullable = false, length = 10)
    private String zipCode;

    @Column(nullable = false, length = 50)
    private String country;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 255)
    private String managerName;

    @Column(length = 255)
    private String managerEmail;

    @Column(nullable = false)
    @Builder.Default
    private Double latitude = 0.0;

    @Column(nullable = false)
    @Builder.Default
    private Double longitude = 0.0;

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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Company> companies = new HashSet<>();

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<InventoryTransaction> inventoryTransactions = new HashSet<>();

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Purchase> purchases = new HashSet<>();

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Sale> sales = new HashSet<>();

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Delivery> deliveries = new HashSet<>();

    public enum WarehouseType {
        CATTLE_FEED,
        GROCERY,
        ELECTRONICS,
        MEDICAL,
        TEXTILE,
        FMCG,
        AGRICULTURE,
        AUTOMOTIVE,
        CHEMICALS,
        OTHER
    }

    public void addCompany(Company company) {
        companies.add(company);
        company.setWarehouse(this);
    }

    public void removeCompany(Company company) {
        companies.remove(company);
        company.setWarehouse(null);
    }
}
