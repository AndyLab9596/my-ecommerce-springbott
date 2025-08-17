package com.andy.my.e_commerce.exception;

import com.andy.my.e_commerce.dtos.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalHandleExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        log.error("Exception:::", exception);
        return ResponseEntity.badRequest().body(
          ApiResponse.builder()
                  .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                  .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
                  .build()
        );
    }
}
