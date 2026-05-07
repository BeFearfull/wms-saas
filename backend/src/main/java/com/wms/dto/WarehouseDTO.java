package com.wms.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseDTO {
    private Long id;
    private String name;
    private String location;
    private String warehouseType;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String contactPersonName;
    private String contactPhone;
    private String contactEmail;
    private Boolean isActive;
    private Double totalCapacity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
