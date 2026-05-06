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
@Table(name = "companies", indexes = {
    @Index(name = "idx_company_warehouse", columnList = "warehouse_id"),
    @Index(name = "idx_company_code", columnList = "company_code")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "company_code", unique = true, nullable = false)
    private String companyCode;
    
    @Column(name = "source_location", nullable = false)
    private String sourceLocation;
    
    @Column(name = "contact_person")
    private String contactPerson;
    
    @Column(name = "contact_phone")
    private String contactPhone;
    
    @Column(name = "contact_email")
    private String contactEmail;
    
    @Column(columnDefinition = "TEXT")
    private String address;
    
    @Column(name = "gst_number")
    private String gstNumber;
    
    @Column(name = "pan_number")
    private String panNumber;
    
    @Column(name = "bank_account")
    private String bankAccount;
    
    @Column(name = "ifsc_code")
    private String ifscCode;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanyType type;
    
    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<PurchaseOrder> purchaseOrders = new HashSet<>();
    
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
    
    public enum CompanyType {
        SUPPLIER("Supplier"),
        CUSTOMER("Customer"),
        DISTRIBUTOR("Distributor"),
        VENDOR("Vendor"),
        MANUFACTURER("Manufacturer");
        
        private final String displayName;
        
        CompanyType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}
