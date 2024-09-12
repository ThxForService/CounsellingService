package com.thxforservice.counseling.exceptions;

import com.thxforservice.global.exceptions.CommonException;

public class CounselingNotFoundException extends CommonException {

    public CounselingNotFoundException() {
        super("NotFound.reservation");
        setErrorCode(true);
    }
}
