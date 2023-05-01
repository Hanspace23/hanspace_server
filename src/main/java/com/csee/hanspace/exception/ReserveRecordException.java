package com.csee.hanspace.exception;

import org.springframework.http.HttpStatus;

public class ReserveRecordException extends HanspaceException{
    protected ReserveRecordException(String message, HttpStatus httpStatus) {super(message, httpStatus);}
}
