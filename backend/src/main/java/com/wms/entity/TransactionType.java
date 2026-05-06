package com.wms.entity;

public enum TransactionType {
    PURCHASE,   // Stock received from supplier
    SALE,       // Stock sold to customer
    ADJUSTMENT, // Manual stock adjustment
    DAMAGE,     // Damaged goods
    RETURN,     // Returned goods
    WASTE,      // Wasted/Expired goods
    TRANSFER    // Transfer between warehouses (future)
}
