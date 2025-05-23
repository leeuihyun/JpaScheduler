package com.scheduler.hyun.service;

import com.scheduler.hyun.domain.dto.schedule.ScheduleCreateRequest;
import com.scheduler.hyun.domain.dto.schedule.ScheduleResponse;
import com.scheduler.hyun.domain.dto.schedule.ScheduleUpdateRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface ScheduleService {

    Long createSchedule(ScheduleCreateRequest scheduleCreateRequest,
        HttpServletRequest httpServletRequest);

    ScheduleResponse findScheduleById(Long scheduleId);

    Long updateSchedule(ScheduleUpdateRequest scheduleUpdateRequest,
        HttpServletRequest httpServletRequest);

    Long deleteSchedule(Long scheduleId, HttpServletRequest httpServletRequest);
}
