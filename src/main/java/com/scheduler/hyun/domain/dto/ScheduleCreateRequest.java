package com.scheduler.hyun.domain.dto;

import com.scheduler.hyun.domain.entity.Schedule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequest {

    @NotBlank
    private String userName;

    @NotBlank
    @Size(min=1, max=100)
    private String scheduleTitle;

    @NotBlank
    private String scheduleContent;

    public Schedule toEntity() {
        return Schedule.builder()
            .scheduleTitle(this.scheduleTitle)
            .scheduleContent(this.scheduleContent)
            .userName(this.userName)
            .build();
    }
}
