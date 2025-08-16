package com.andy.my.e_commerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EMAIL_EXISTED(1002, "User's email existed", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1002, "Uncategorized error", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Password must be between {min} and {max} characters", HttpStatus.BAD_REQUEST)
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
