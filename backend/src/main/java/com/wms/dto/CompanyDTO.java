package com.wms.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDTO {
    private Long id;
    private String name;
    private String companyType;
    private String sourceLocation;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String contactPersonName;
    private String contactPhone;
    private String contactEmail;
    private String gstin;
    private String pan;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
