package com.wms.entity;

public enum DeliveryStatus {
    PENDING,       // Delivery not yet started
    DISPATCHED,    // Package dispatched
    IN_TRANSIT,    // In transit
    OUT_FOR_DELIVERY,
    DELIVERED,     // Successfully delivered
    FAILED,        // Delivery failed
    DELAYED,       // Delivery delayed
    CANCELLED      // Delivery cancelled
}
