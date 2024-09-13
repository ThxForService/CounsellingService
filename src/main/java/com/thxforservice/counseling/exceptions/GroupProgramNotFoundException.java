package com.thxforservice.counseling.exceptions;

import com.thxforservice.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class GroupProgramNotFoundException extends CommonException {

    public GroupProgramNotFoundException() {
        super("AlreadyFull.GroupProgram", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
