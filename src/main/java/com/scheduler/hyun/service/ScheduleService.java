package com.scheduler.hyun.service;

import com.scheduler.hyun.domain.dto.ScheduleCreateRequest;
import com.scheduler.hyun.domain.dto.ScheduleUpdateRequest;
import com.scheduler.hyun.domain.entity.Schedule;

public interface ScheduleService {
    Long createSchedule(ScheduleCreateRequest scheduleCreateRequest);

    Schedule findScheduleById(Long scheduleId) throws Exception;

    Long updateSchedule(ScheduleUpdateRequest scheduleUpdateRequest) throws Exception;

    Long deleteSchedule(Long scheduleId) throws Exception;
}
