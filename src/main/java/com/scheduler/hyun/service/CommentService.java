package com.scheduler.hyun.service;

import com.scheduler.hyun.domain.dto.comment.CommentCreateRequest;
import com.scheduler.hyun.domain.dto.comment.CommentResponse;
import com.scheduler.hyun.domain.dto.comment.CommentUpdateRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface CommentService {

    Long createComment(CommentCreateRequest commentCreateRequest,
        HttpServletRequest httpServletRequest);

    CommentResponse findCommentById(Long commentId);

    Long updateComment(CommentUpdateRequest commentUpdateRequest,
        HttpServletRequest httpServletRequest);

    Long deleteComment(Long commentId, HttpServletRequest httpServletRequest);
}
