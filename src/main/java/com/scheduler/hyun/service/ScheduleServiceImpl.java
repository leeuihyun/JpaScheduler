package com.scheduler.hyun.service;

import com.scheduler.hyun.domain.dto.schedule.ScheduleCreateRequest;
import com.scheduler.hyun.domain.dto.schedule.ScheduleResponse;
import com.scheduler.hyun.domain.dto.schedule.ScheduleUpdateRequest;
import com.scheduler.hyun.domain.entity.Schedule;
import com.scheduler.hyun.domain.entity.User;
import com.scheduler.hyun.enums.ErrorEnum;
import com.scheduler.hyun.exception.ScheduleException;
import com.scheduler.hyun.repository.ScheduleJpaRepository;
import com.scheduler.hyun.utils.AuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleJpaRepository scheduleJpaRepository;
    private final AuthUtils authUtils;

    @Transactional
    @Override
    public Long createSchedule(ScheduleCreateRequest scheduleCreateRequest,
        HttpServletRequest httpServletRequest) {

        User user = authUtils.authorizeUser(scheduleCreateRequest, httpServletRequest);
        Schedule schedule = scheduleJpaRepository.save(scheduleCreateRequest.toEntity(user));

        return schedule.getScheduleId();
    }

    @Override
    public ScheduleResponse findScheduleById(Long scheduleId) {
        return scheduleJpaRepository.findById(scheduleId)
            .orElseThrow(() -> new ScheduleException(ErrorEnum.NO_EXIST_SCHEDULE)).toScheduleDto();
    }

    @Transactional
    @Override
    public Long updateSchedule(ScheduleUpdateRequest scheduleUpdateRequest,
        HttpServletRequest httpServletRequest) {

        Optional<Schedule> optionalSchedule = scheduleJpaRepository
            .findById(scheduleUpdateRequest.getScheduleId());

        if (optionalSchedule.isEmpty()) {
            throw new ScheduleException(ErrorEnum.NO_EXIST_SCHEDULE);
        }

        Schedule schedule = optionalSchedule.get();
        authUtils.authorizeUser(schedule.getUser().getUserId(), httpServletRequest);
        schedule.updateSchedule(scheduleUpdateRequest);

        return schedule.getScheduleId();
    }

    @Transactional
    @Override
    public Long deleteSchedule(Long scheduleId, HttpServletRequest httpServletRequest) {
        Schedule schedule = scheduleJpaRepository.findById(scheduleId)
            .orElseThrow(() -> new ScheduleException(ErrorEnum.NO_EXIST_SCHEDULE));

        authUtils.authorizeUser(schedule.getUser().getUserId(), httpServletRequest);
        scheduleJpaRepository.delete(schedule);

        return schedule.getScheduleId();
    }
}
