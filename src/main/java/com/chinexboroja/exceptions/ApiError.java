package com.chinexboroja.exceptions;

import org.springframework.http.HttpStatus;

public class ApiError {

    private final HttpStatus httpStatus;

    private final String message;

    public ApiError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

}
