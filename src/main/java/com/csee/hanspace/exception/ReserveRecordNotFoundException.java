package com.csee.hanspace.exception;

import org.springframework.http.HttpStatus;

public class ReserveRecordNotFoundException extends ReserveRecordException{
    public ReserveRecordNotFoundException() {super("해당 예약은 존재하지 않습니다.", HttpStatus.BAD_REQUEST);}
}
