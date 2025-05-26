package com.scheduler.hyun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    // custom
    NO_EXIST_SCHEDULE(404, "존재하지 않는 일정입니다."),
    NO_EXIST_USER(401, "존재하지 않는 유저입니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 올바르지 않습니다."),
    NO_AUTHORIZATION(403, "권한이 없습니다."),
    NO_COMMENT(404, "존재하지 않는 댓글입니다.");

    private final Integer httpStatus;
    private final String errorMessage;
}
