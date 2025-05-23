package com.scheduler.hyun.domain.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateRequest {

    @NotNull
    private Long scheduleId;

    @NotBlank
    @Size(min = 1, max = 10)
    private String scheduleTitle;

    @NotBlank
    @Size(min = 1)
    private String scheduleContent;
}
