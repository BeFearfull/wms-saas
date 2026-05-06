package com.wms.entity;

public enum PurchaseStatus {
    PENDING,        // Order placed, awaiting confirmation
    CONFIRMED,      // Supplier confirmed the order
    DISPATCHED,     // Order shipped from supplier
    IN_TRANSIT,     // Order in transit
    DELIVERED,      // Order delivered to warehouse
    COMPLETED,      // Payment completed
    CANCELLED       // Order cancelled
}
