package com.daud.datamaster.exception.controller;

import com.daud.datamaster.exception.ResourceNotFoundException;
import com.daud.datamaster.exception.dto.ErrorCode;
import com.daud.datamaster.exception.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        ErrorResponseDTO dto = ErrorResponseDTO.of("data not found", ErrorCode.DATA_NOT_FOUND, details, HttpStatus.BAD_REQUEST);
        return  ResponseEntity.badRequest().body(dto);
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }

        ErrorResponseDTO dto = ErrorResponseDTO.of("invalid data", ErrorCode.INVALID_DATA, details, HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(dto);
    }
}
