package com.wms.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryDTO {
    private Long id;
    private String deliveryNumber;
    private String deliveryType;
    private String partyName;
    private String contactNumber;
    private Double quantity;
    private LocalDate expectedDate;
    private LocalDate actualDate;
    private String status;
    private String transporterName;
    private String trackingNumber;
    private String vehicleNumber;
    private String driverName;
    private String driverPhone;
    private String notes;
    private Boolean isDelayed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
