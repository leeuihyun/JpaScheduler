package com.scheduler.hyun.service;

import com.scheduler.hyun.domain.dto.schedule.ScheduleCreateRequest;
import com.scheduler.hyun.domain.dto.schedule.ScheduleResponse;
import com.scheduler.hyun.domain.dto.schedule.ScheduleUpdateRequest;
import com.scheduler.hyun.domain.entity.Schedule;
import com.scheduler.hyun.domain.entity.User;
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
        HttpServletRequest httpServletRequest) throws Exception {

        User user = authUtils.authorizeUser(scheduleCreateRequest, httpServletRequest);
        Schedule schedule = scheduleJpaRepository.save(scheduleCreateRequest.toEntity(user));

        return schedule.getScheduleId();
    }

    @Override
    public ScheduleResponse findScheduleById(Long scheduleId) throws Exception {
        return scheduleJpaRepository.findById(scheduleId)
            .orElseThrow(() -> new Exception("존재하지 않는 스케줄입니다.")).toScheduleDto();
    }

    @Transactional
    @Override
    public Long updateSchedule(ScheduleUpdateRequest scheduleUpdateRequest,
        HttpServletRequest httpServletRequest) throws Exception {

        Optional<Schedule> optionalSchedule = scheduleJpaRepository
            .findById(scheduleUpdateRequest.getScheduleId());

        if (optionalSchedule.isEmpty()) {
            throw new Exception("존재하지 않는 스케줄입니다.");
        }

        Schedule schedule = optionalSchedule.get();
        authUtils.authorizeUser(schedule.getUser().getUserId(), httpServletRequest);
        schedule.updateSchedule(scheduleUpdateRequest);

        return schedule.getScheduleId();
    }

    @Transactional
    @Override
    public Long deleteSchedule(Long scheduleId, HttpServletRequest httpServletRequest)
        throws Exception {
        Schedule schedule = scheduleJpaRepository.findById(scheduleId)
            .orElseThrow(() -> new Exception("존재하지 않는 스케줄입니다."));

        authUtils.authorizeUser(schedule.getUser().getUserId(), httpServletRequest);
        scheduleJpaRepository.delete(schedule);

        return schedule.getScheduleId();
    }
}
