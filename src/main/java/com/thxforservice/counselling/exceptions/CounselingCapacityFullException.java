package com.thxforservice.counselling.exceptions;

import com.thxforservice.global.exceptions.CommonException;

public class CounselingCapacityFullException extends CommonException {

    public CounselingCapacityFullException() {
        super("FullCapacity.reservation");
        setErrorCode(true);
    }
}
