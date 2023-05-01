package com.csee.hanspace.exception;

import org.springframework.http.HttpStatus;

public class HanspaceException extends RuntimeException {
    private final HttpStatus httpStatus;

    public HanspaceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {return httpStatus;}
}
