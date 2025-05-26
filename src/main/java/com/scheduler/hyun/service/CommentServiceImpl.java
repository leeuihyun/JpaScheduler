package com.scheduler.hyun.service;

import com.scheduler.hyun.domain.dto.comment.CommentCreateRequest;
import com.scheduler.hyun.domain.dto.comment.CommentResponse;
import com.scheduler.hyun.domain.dto.comment.CommentUpdateRequest;
import com.scheduler.hyun.domain.entity.Comment;
import com.scheduler.hyun.domain.entity.Schedule;
import com.scheduler.hyun.domain.entity.User;
import com.scheduler.hyun.enums.ErrorEnum;
import com.scheduler.hyun.exception.ScheduleException;
import com.scheduler.hyun.repository.CommentJpaRepository;
import com.scheduler.hyun.repository.ScheduleJpaRepository;
import com.scheduler.hyun.utils.AuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentJpaRepository commentJpaRepository;
    private final ScheduleJpaRepository scheduleJpaRepository;
    private final AuthUtils authUtils;

    @Transactional
    @Override
    public Long createComment(CommentCreateRequest commentCreateRequest,
        HttpServletRequest httpServletRequest) {

        User user = authUtils.authorizeUser(commentCreateRequest.getUserId(), httpServletRequest);
        Schedule schedule = scheduleJpaRepository.findById(commentCreateRequest.getScheduleId())
            .orElseThrow(() -> new ScheduleException(
                ErrorEnum.NO_EXIST_SCHEDULE));

        Comment comment = Comment.builder()
            .commentContent(commentCreateRequest.getCommentContent())
            .user(user)
            .schedule(schedule)
            .build();

        return commentJpaRepository.save(comment).getCommentId();
    }

    @Transactional
    @Override
    public CommentResponse findCommentById(Long commentId) {
        Comment comment = commentJpaRepository.findById(commentId)
            .orElseThrow(() -> new ScheduleException(ErrorEnum.NO_COMMENT));

        return CommentResponse.builder()
            .commentId(comment.getCommentId())
            .commentContent(comment.getCommentContent())
            .userId(comment.getUser().getUserId())
            .scheduleId(comment.getSchedule().getScheduleId())
            .createdAt(comment.getCreatedAt())
            .updatedAt(comment.getUpdatedAt())
            .build();
    }

    @Transactional
    @Override
    public Long updateComment(CommentUpdateRequest commentUpdateRequest,
        HttpServletRequest httpServletRequest) {

        Comment comment = commentJpaRepository.findById(commentUpdateRequest.getCommentId())
            .orElseThrow(() -> new ScheduleException(ErrorEnum.NO_COMMENT));

        authUtils.authorizeUser(comment.getUser().getUserId(), httpServletRequest);
        comment.updateSchedule(commentUpdateRequest.getCommentContent());

        return comment.getCommentId();
    }

    @Transactional
    @Override
    public Long deleteComment(Long commentId, HttpServletRequest httpServletRequest) {
        Comment comment = commentJpaRepository.findById(commentId)
            .orElseThrow(() -> new ScheduleException(ErrorEnum.NO_COMMENT));

        authUtils.authorizeUser(comment.getUser().getUserId(), httpServletRequest);
        commentJpaRepository.delete(comment);

        return comment.getCommentId();
    }
}
