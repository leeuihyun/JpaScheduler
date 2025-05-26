package com.scheduler.hyun.domain.dto.schedule;

import com.scheduler.hyun.domain.dto.user.model.UserIdModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequest implements UserIdModel {

    @NotNull
    private Long userId;

    @NotBlank
    @Size(min = 1, max = 10)
    private String scheduleTitle;

    @NotBlank
    @Size(min = 1)
    private String scheduleContent;
}
