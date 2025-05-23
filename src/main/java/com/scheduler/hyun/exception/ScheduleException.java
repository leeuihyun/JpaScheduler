package com.scheduler.hyun.exception;

import com.scheduler.hyun.enums.ErrorEnum;
import lombok.Getter;

@Getter
public class ScheduleException extends RuntimeException {

    private final Integer httpStatus;
    private final String errorMessage;

    public ScheduleException(ErrorEnum errorEnum) {
        super(errorEnum.getErrorMessage());
        this.httpStatus = errorEnum.getHttpStatus();
        this.errorMessage = errorEnum.getErrorMessage();
    }
}
