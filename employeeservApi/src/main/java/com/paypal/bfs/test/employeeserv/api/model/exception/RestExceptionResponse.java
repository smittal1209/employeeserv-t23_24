package com.paypal.bfs.test.employeeserv.api.model.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestExceptionResponse {
    private String code;
    private String message;
    private LocalDateTime timestamp;
}
