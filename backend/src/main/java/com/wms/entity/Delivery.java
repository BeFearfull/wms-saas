package com.wms.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "deliveries", indexes = {
    @Index(name = "idx_delivery_warehouse", columnList = "warehouse_id"),
    @Index(name = "idx_delivery_status", columnList = "status"),
    @Index(name = "idx_delivery_type", columnList = "delivery_type")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @Column(name = "delivery_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType; // INCOMING or OUTGOING

    @Column(name = "delivery_number", nullable = false, unique = true)
    private String deliveryNumber;

    @Column(name = "party_name", nullable = false)
    private String partyName; // Supplier (INCOMING) or Customer (OUTGOING)

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @Column(name = "expected_date", nullable = false)
    private LocalDate expectedDate;

    @Column(name = "actual_date")
    private LocalDate actualDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status = DeliveryStatus.PENDING;

    @Column(name = "transporter_name")
    private String transporterName;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "vehicle_number")
    private String vehicleNumber;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "driver_phone")
    private String driverPhone;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "is_delayed")
    private Boolean isDelayed = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
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
        INCOMING, OUTGOING
    }

    public enum DeliveryStatus {
        PENDING, IN_TRANSIT, DELAYED, DELIVERED, CANCELLED, PARTIAL
    }
}
