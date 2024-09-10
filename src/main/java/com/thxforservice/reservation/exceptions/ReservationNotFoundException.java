package com.thxforservice.reservation.exceptions;

import com.thxforservice.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class ReservationNotFoundException extends CommonException {
    public ReservationNotFoundException() {
        super("NotFound.counseling", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
