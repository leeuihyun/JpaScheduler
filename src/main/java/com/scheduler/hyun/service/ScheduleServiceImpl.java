package com.scheduler.hyun.service;

import com.scheduler.hyun.domain.dto.schedule.ScheduleCreateRequest;
import com.scheduler.hyun.domain.dto.schedule.ScheduleResponse;
import com.scheduler.hyun.domain.dto.schedule.ScheduleUpdateRequest;
import com.scheduler.hyun.domain.entity.Schedule;
import com.scheduler.hyun.domain.entity.User;
import com.scheduler.hyun.repository.ScheduleJpaRepository;
import com.scheduler.hyun.repository.UserJpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleJpaRepository scheduleJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public ScheduleServiceImpl(ScheduleJpaRepository scheduleJpaRepository,
        UserJpaRepository userJpaRepository) {
        this.scheduleJpaRepository = scheduleJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Transactional
    @Override
    public Long createSchedule(ScheduleCreateRequest scheduleCreateRequest) throws Exception {
        User user = userJpaRepository.findById(scheduleCreateRequest.getUserId())
            .orElseThrow(() -> new Exception("존재하지 않는 유저입니다."));

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
    public Long updateSchedule(ScheduleUpdateRequest scheduleUpdateRequest) throws Exception {
        Optional<Schedule> optionalSchedule = scheduleJpaRepository
            .findById(scheduleUpdateRequest.getScheduleId());

        if (optionalSchedule.isEmpty()) {
            throw new Exception("존재하지 않는 스케줄입니다.");
        }

        Schedule schedule = optionalSchedule.get();
        schedule.updateSchedule(scheduleUpdateRequest);

        return schedule.getScheduleId();
    }

    @Transactional
    @Override
    public Long deleteSchedule(Long scheduleId) throws Exception {
        Schedule schedule = scheduleJpaRepository.findById(scheduleId)
            .orElseThrow(() -> new Exception("존재하지 않는 스케줄입니다."));

        scheduleJpaRepository.delete(schedule);

        return schedule.getScheduleId();
    }
}
