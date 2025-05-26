package com.scheduler.hyun.domain.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentUpdateRequest {

    @NotNull
    private Long commentId;

    @NotBlank
    private String commentContent;
}
