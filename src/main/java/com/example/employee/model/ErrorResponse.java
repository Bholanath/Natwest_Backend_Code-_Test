package com.example.employee.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
@Builder
public class ErrorResponse {
    private final String id;
    private final ErrorCode errorCode;
    private final String errorMessage;
    private final LocalDateTime errorOccurredAt;
}
