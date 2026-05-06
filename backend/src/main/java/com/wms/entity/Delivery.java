package com.wms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries", indexes = {
    @Index(name = "idx_delivery_warehouse", columnList = "warehouse_id"),
    @Index(name = "idx_delivery_status", columnList = "status")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_order_id")
    private SalesOrder salesOrder;
    
    @Column(name = "delivery_number", unique = true, nullable = false)
    private String deliveryNumber;
    
    @Column(name = "delivery_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    
    @Column(name = "expected_date")
    private LocalDate expectedDate;
    
    @Column(name = "actual_date")
    private LocalDate actualDate;
    
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    
    @Column(name = "transporter_name")
    private String transporterName;
    
    @Column(name = "transporter_phone")
    private String transporterPhone;
    
    @Column(name = "tracking_number")
    private String trackingNumber;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
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
    
    public enum DeliveryType {
        INCOMING("Incoming"),
        OUTGOING("Outgoing");
        
        private final String displayName;
        
        DeliveryType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    public enum DeliveryStatus {
        PENDING("Pending"),
        IN_TRANSIT("In Transit"),
        DELIVERED("Delivered"),
        DELAYED("Delayed"),
        CANCELLED("Cancelled");
        
        private final String displayName;
        
        DeliveryStatus(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}
