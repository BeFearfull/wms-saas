package com.wms.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
}
