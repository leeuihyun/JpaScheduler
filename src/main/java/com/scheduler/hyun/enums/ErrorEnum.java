package com.scheduler.hyun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    // valid error
    WRONG_EMAIL_TYPE(404, "잘못된 이메일 형식입니다."),
    WRONG_USER_ID(404, "존재하지 않는 사용자 식별자입니다."),

    // custom
    NO_EXIST_SCHEDULE(404, "존재하지 않는 일정입니다."),
    NO_EXIST_USER(404, "존재하지 않는 유저입니다."),
    PASSWORD_MISMATCH(404, "비밀번호가 올바르지 않습니다."),
    NO_AUTHORIZATION(403, "권한이 없습니다.");

    private final Integer httpStatus;
    private final String errorMessage;
}
