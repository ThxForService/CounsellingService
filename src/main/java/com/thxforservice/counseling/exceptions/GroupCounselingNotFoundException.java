package com.thxforservice.counseling.exceptions;

import com.thxforservice.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class GroupCounselingNotFoundException extends CommonException {

    public GroupCounselingNotFoundException() {
        super("NotFound.groupReservation", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
