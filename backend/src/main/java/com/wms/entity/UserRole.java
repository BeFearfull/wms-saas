package com.wms.entity;

public enum UserRole {
    ADMIN,           // Full system access
    WAREHOUSE_OWNER, // Can manage their own warehouse
    MANAGER,         // Can manage operations in assigned warehouse
    STAFF            // Limited access for data entry
}
