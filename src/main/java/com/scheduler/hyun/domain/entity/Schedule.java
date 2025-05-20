package com.scheduler.hyun.domain.entity;

import com.scheduler.hyun.domain.dto.schedule.ScheduleResponse;
import com.scheduler.hyun.domain.dto.schedule.ScheduleUpdateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "schedule_title")
    private String scheduleTitle;

    @Column(name = "schedule_content")
    private String scheduleContent;

    public void updateSchedule(ScheduleUpdateRequest scheduleUpdateRequest) {
        this.scheduleTitle = scheduleUpdateRequest.getScheduleTitle();
        this.scheduleContent = scheduleUpdateRequest.getScheduleContent();
    }

    public ScheduleResponse toScheduleDto() {
        return ScheduleResponse.builder()
            .scheduleId(this.scheduleId)
            .scheduleTitle(this.scheduleTitle)
            .scheduleContent(this.scheduleContent)
            .userId(this.user.getUserId())
            .createdAt(this.getCreatedAt())
            .updatedAt(this.getUpdatedAt())
            .build();
    }
}
