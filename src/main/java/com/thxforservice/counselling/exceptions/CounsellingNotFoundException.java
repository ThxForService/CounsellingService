package com.thxforservice.counselling.exceptions;

import com.thxforservice.global.exceptions.CommonException;

public class CounsellingNotFoundException extends CommonException {

    public CounsellingNotFoundException() {
        super("NotFound.reservation");
        setErrorCode(true);
    }
}
