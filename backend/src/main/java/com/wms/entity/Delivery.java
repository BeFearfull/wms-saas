package com.wms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deliveries", indexes = {
    @Index(name = "idx_warehouse_id", columnList = "warehouse_id"),
    @Index(name = "idx_delivery_date", columnList = "delivery_date")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @Column(nullable = false)
    private String deliveryNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryType type; // INCOMING or OUTGOING

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    private String companyName;
    private String contactName;
    private String contactPhone;

    private String address;
    private String city;
    private String state;
    private String pincode;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal totalQuantity;

    @Column(nullable = false)
    private LocalDateTime deliveryDate;

    private LocalDateTime actualDeliveryDate;

    private String transporterName;
    private String transporterPhone;
    private String awbNumber;
    private String vehicleNumber;
    private String driverName;
    private String driverPhone;

    private String notes;
    private String delayReason;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = DeliveryStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
