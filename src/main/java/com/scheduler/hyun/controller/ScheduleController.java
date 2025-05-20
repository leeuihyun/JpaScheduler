package com.scheduler.hyun.controller;

import com.scheduler.hyun.domain.dto.schedule.ScheduleCreateRequest;
import com.scheduler.hyun.domain.dto.schedule.ScheduleIdResponse;
import com.scheduler.hyun.domain.dto.schedule.ScheduleResponse;
import com.scheduler.hyun.domain.dto.schedule.ScheduleUpdateRequest;
import com.scheduler.hyun.service.ScheduleService;
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
        (@RequestBody ScheduleCreateRequest scheduleCreateRequest) throws Exception {
        Long scheduleId = scheduleService.createSchedule(scheduleCreateRequest);
        return ResponseEntity.ok().body(new ScheduleIdResponse(scheduleId));
    }

    @GetMapping("/search/{scheduleId}")
    public ResponseEntity<ScheduleResponse> searchSchedule(@PathVariable Long scheduleId)
        throws Exception {
        ScheduleResponse schedule = scheduleService.findScheduleById(scheduleId);
        return ResponseEntity.ok().body(schedule);
    }

    @PutMapping("/update")
    public ResponseEntity<ScheduleIdResponse> updateSchedule
        (@RequestBody ScheduleUpdateRequest scheduleUpdateRequest) throws Exception {
        Long scheduleId = scheduleService.updateSchedule(scheduleUpdateRequest);
        return ResponseEntity.ok().body(new ScheduleIdResponse(scheduleId));
    }

    @DeleteMapping("/delete/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId) throws Exception {
        Long deletedScheduleId = scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.ok().body(new ScheduleIdResponse(deletedScheduleId));
    }

}
