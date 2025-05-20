package com.scheduler.hyun.service;

import com.scheduler.hyun.domain.dto.schedule.ScheduleCreateRequest;
import com.scheduler.hyun.domain.dto.schedule.ScheduleResponse;
import com.scheduler.hyun.domain.dto.schedule.ScheduleUpdateRequest;

public interface ScheduleService {

    Long createSchedule(ScheduleCreateRequest scheduleCreateRequest) throws Exception;

    ScheduleResponse findScheduleById(Long scheduleId) throws Exception;

    Long updateSchedule(ScheduleUpdateRequest scheduleUpdateRequest) throws Exception;

    Long deleteSchedule(Long scheduleId) throws Exception;
}
