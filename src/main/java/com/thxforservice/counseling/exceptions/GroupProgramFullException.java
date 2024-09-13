package com.thxforservice.counseling.exceptions;

import com.thxforservice.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class GroupProgramFullException extends CommonException {

    public GroupProgramFullException() {
        super("NotFound.GroupProgram", HttpStatus.CONFLICT);
        setErrorCode(true);
    }
}
