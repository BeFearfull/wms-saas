package com.wms.dto;

import com.wms.entity.UserRole;
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
public class UserDTO {
    private UUID id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String profileImage;
    private UserRole role;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
