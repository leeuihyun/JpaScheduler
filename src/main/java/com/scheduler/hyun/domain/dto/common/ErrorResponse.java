package com.scheduler.hyun.domain.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private Integer httpStatus;
    private String errorMessage;
}
