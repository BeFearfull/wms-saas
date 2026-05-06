package com.wms.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Delivery Entity - Tracks incoming and outgoing deliveries
 */
@Entity
@Table(name = "deliveries", indexes = {
        @Index(name = "idx_delivery_warehouse_id", columnList = "warehouse_id"),
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

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Column(nullable = false, length = 255)
    private String partyName; // Supplier name for incoming, Customer name for outgoing

    @Column(length = 20)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDateTime expectedDeliveryDate;

    @Column(nullable = false)
    private Long quantity;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private DeliveryStatus status = DeliveryStatus.PENDING;

    @Column(length = 255)
    private String transporterName;

    @Column(length = 20)
    private String transporterPhoneNumber;

    @Column(length = 50)
    private String transporterVehicleNumber;

    @Column(length = 500)
    private String notes;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDelayed = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    public enum DeliveryType {
        INCOMING,
        OUTGOING
    }

    public enum DeliveryStatus {
        PENDING,
        IN_TRANSIT,
        DELIVERED,
        CANCELLED,
        DELAYED
    }
}
