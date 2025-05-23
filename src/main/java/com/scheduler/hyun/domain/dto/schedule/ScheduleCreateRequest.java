package com.scheduler.hyun.domain.dto.schedule;

import com.scheduler.hyun.domain.dto.user.model.UserIdModel;
import com.scheduler.hyun.domain.entity.Schedule;
import com.scheduler.hyun.domain.entity.User;
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

    public Schedule toEntity(User user) {
        return Schedule.builder()
            .scheduleTitle(this.scheduleTitle)
            .scheduleContent(this.scheduleContent)
            .user(user)
            .build();
    }
}
