package com.daud.datamaster.exception.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponseDTO {

    private LocalDateTime timestamp;

    private String message;

    private ErrorCode errorCode;

    private List<String> details;

    private HttpStatus status;

    public ErrorResponseDTO(String message, ErrorCode errorCode, List<String> details, HttpStatus status) {

        super();
        this.message = message;
        this.errorCode = errorCode;
        this.details = details;
        this.status = status;
    }

    public static ErrorResponseDTO of(final String message, final ErrorCode errorCode,
                                      final List<String> details, HttpStatus status) {

        return new ErrorResponseDTO(message, errorCode, details, status);
    }
}
