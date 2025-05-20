package com.scheduler.hyun.domain.entity;

import com.scheduler.hyun.domain.dto.ScheduleUpdateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "schedule_title")
    private String scheduleTitle;

    @Column(name = "schedule_content")
    private String scheduleContent;

    public void updateSchedule(ScheduleUpdateRequest scheduleUpdateRequest) {
        this.scheduleTitle = scheduleUpdateRequest.getScheduleTitle();
        this.scheduleContent = scheduleUpdateRequest.getScheduleContent();
    }
}
