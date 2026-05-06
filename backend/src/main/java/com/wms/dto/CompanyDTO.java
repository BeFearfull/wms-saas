package com.wms.dto;

import com.wms.entity.CompanyType;
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
public class CompanyDTO {
    private UUID id;
    private String name;
    private CompanyType type;
    private String contactPerson;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String gstin;
    private String panNumber;
    private String sourceLocation;
    private String bankAccountNumber;
    private String ifscCode;
    private String bankName;
    private String notes;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
