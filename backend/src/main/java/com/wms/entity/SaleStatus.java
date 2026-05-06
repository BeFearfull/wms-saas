package com.wms.entity;

public enum SaleStatus {
    PENDING,        // Order placed, awaiting confirmation
    CONFIRMED,      // Customer confirmed the order
    PACKED,         // Items packed
    DISPATCHED,     // Order shipped
    IN_TRANSIT,     // Order in transit
    DELIVERED,      // Order delivered
    COMPLETED,      // Payment completed
    CANCELLED,      // Order cancelled
    RETURNED        // Order returned
}
