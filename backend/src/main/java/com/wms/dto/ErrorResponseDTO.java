package com.wms.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private String message;
    private int statusCode;
    private long timestamp;

    public ErrorResponseDTO(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.statusCode = 400;
    }
}
