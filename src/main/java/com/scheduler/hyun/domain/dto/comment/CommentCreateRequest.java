package com.scheduler.hyun.domain.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreateRequest {

    @NotBlank
    private String commentContent;

    @NotNull
    private Long userId;
    
    @NotNull
    private Long scheduleId;
}
