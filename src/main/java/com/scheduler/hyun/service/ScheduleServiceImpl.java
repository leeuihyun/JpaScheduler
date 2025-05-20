package com.scheduler.hyun.service;

import com.scheduler.hyun.domain.dto.ScheduleCreateRequest;
import com.scheduler.hyun.domain.dto.ScheduleUpdateRequest;
import com.scheduler.hyun.domain.entity.Schedule;
import com.scheduler.hyun.repository.ScheduleJpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleJpaRepository scheduleJpaRepository;

    public ScheduleServiceImpl(ScheduleJpaRepository scheduleJpaRepository) {
        this.scheduleJpaRepository = scheduleJpaRepository;
    }

    @Override
    public Long createSchedule(ScheduleCreateRequest scheduleCreateRequest) {
        Schedule schedule = scheduleJpaRepository.save(scheduleCreateRequest.toEntity());
        return schedule.getScheduleId();
    }

    @Override
    public Schedule findScheduleById(Long scheduleId) throws Exception {
        return scheduleJpaRepository.findById(scheduleId)
            .orElseThrow(() -> new Exception("존재하지 않는 스케줄입니다."));
    }

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

    @Override
    public Long deleteSchedule(Long scheduleId) throws Exception {
        Schedule schedule = scheduleJpaRepository.findById(scheduleId)
            .orElseThrow(() -> new Exception("존재하지 않는 스케줄입니다."));

        scheduleJpaRepository.delete(schedule);

        return schedule.getScheduleId();
    }
}
