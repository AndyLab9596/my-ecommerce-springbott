package com.andy.my.e_commerce.exception;

import com.andy.my.e_commerce.dtos.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

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

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }

     @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        Map<String, Object> attributes = null;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
            var constraintViolation =
                    exception.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);

            attributes = constraintViolation.getConstraintDescriptor().getAttributes();

            log.info(attributes.toString());

        }catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());

        String errorResponseMessage = Objects.nonNull(attributes)
                ? mapAttribute(errorCode.getMessage(), attributes) : errorCode.getMessage();

        apiResponse.setMessage(errorResponseMessage);

        return ResponseEntity.badRequest().body(apiResponse);
     }

     /*
     attributes will be like this:
     {min=5, message=USER_PASSWORD_IS_NOT_VALID, max=10, ...}
     message will be like this:
     password is from {min} to {max} characters
     Output: password is from 5 to 10 characters
      */
     private String mapAttribute(String message, Map<String, Object> attributes) {
        String resolvedMessage = message;
        for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
            String key = attribute.getKey();
            Object value = attribute.getValue();

            if (value != null) {
                resolvedMessage = resolvedMessage.replace("{" + key + "}", value.toString());
            }
        }
        return resolvedMessage;
     }
}
