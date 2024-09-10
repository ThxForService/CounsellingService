package com.thxforservice.reservation.exceptions;

import com.thxforservice.global.exceptions.CommonException;

public class ReservationNotFoundException extends CommonException {

    public ReservationNotFoundException() {
        super("NotFound.reservation");
        setErrorCode(true);
    }
}
