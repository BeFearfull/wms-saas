package com.wms.dto;

import com.wms.entity.WarehouseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseDTO {
    private UUID id;
    private String name;
    private String description;
    private String location;
    private String city;
    private String state;
    private String pincode;
    private WarehouseType type;
    private String contactPerson;
    private String contactPhone;
    private String contactEmail;
    private Double area;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
