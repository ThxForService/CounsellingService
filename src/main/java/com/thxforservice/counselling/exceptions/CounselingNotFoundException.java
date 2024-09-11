package com.thxforservice.counselling.exceptions;

import com.thxforservice.global.exceptions.CommonException;

public class CounselingNotFoundException extends CommonException {

    public CounselingNotFoundException() {
        super("NotFound.counselling");
        setErrorCode(true);

    }
}
