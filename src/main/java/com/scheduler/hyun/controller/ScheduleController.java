package com.scheduler.hyun.controller;

import com.scheduler.hyun.domain.dto.schedule.ScheduleCreateRequest;
import com.scheduler.hyun.domain.dto.schedule.ScheduleIdResponse;
import com.scheduler.hyun.domain.dto.schedule.ScheduleResponse;
import com.scheduler.hyun.domain.dto.schedule.ScheduleUpdateRequest;
import com.scheduler.hyun.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {

        this.scheduleService = scheduleService;
    }

    @PostMapping("/create")
    public ResponseEntity<ScheduleIdResponse> createSchedule
        (@RequestBody @Valid ScheduleCreateRequest scheduleCreateRequest,
            HttpServletRequest httpServletRequest) {

        Long scheduleId = scheduleService.createSchedule(scheduleCreateRequest, httpServletRequest);
        return ResponseEntity.ok().body(new ScheduleIdResponse(scheduleId));
    }

    @GetMapping("/search/{scheduleId}")
    public ResponseEntity<ScheduleResponse> searchSchedule(@PathVariable Long scheduleId) {

        ScheduleResponse schedule = scheduleService.findScheduleById(scheduleId);
        return ResponseEntity.ok().body(schedule);
    }

    @PutMapping("/update")
    public ResponseEntity<ScheduleIdResponse> updateSchedule
        (@RequestBody @Valid ScheduleUpdateRequest scheduleUpdateRequest,
            HttpServletRequest httpServletRequest) {

        Long scheduleId = scheduleService.updateSchedule(scheduleUpdateRequest, httpServletRequest);
        return ResponseEntity.ok().body(new ScheduleIdResponse(scheduleId));
    }

    @DeleteMapping("/delete/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId,
        HttpServletRequest httpServletRequest) {

        Long deletedScheduleId = scheduleService.deleteSchedule(scheduleId, httpServletRequest);
        return ResponseEntity.ok().body(new ScheduleIdResponse(deletedScheduleId));
    }
}
