package com.andy.my.e_commerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized code", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1000, "invalid parameters", HttpStatus.BAD_REQUEST),
    USER_EMAIL_EXISTED(1001, "user's with email is existed", HttpStatus.BAD_REQUEST),
    USER_NAME_IS_REQUIRED(1002, "user's name must not be blank", HttpStatus.BAD_REQUEST),
    USER_PASSWORD_IS_NOT_VALID(1003, "password is from {min} to {max} characters", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
