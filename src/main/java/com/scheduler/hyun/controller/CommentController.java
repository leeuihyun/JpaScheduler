package com.scheduler.hyun.controller;

import com.scheduler.hyun.domain.dto.comment.CommentCreateRequest;
import com.scheduler.hyun.domain.dto.comment.CommentIdResponse;
import com.scheduler.hyun.domain.dto.comment.CommentResponse;
import com.scheduler.hyun.domain.dto.comment.CommentUpdateRequest;
import com.scheduler.hyun.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<CommentIdResponse> createComment
        (@RequestBody @Valid CommentCreateRequest commentCreateRequest,
            HttpServletRequest httpServletRequest) {

        Long commentId = commentService.createComment(commentCreateRequest,
            httpServletRequest);
        return ResponseEntity.ok().body(new CommentIdResponse(commentId));
    }

    @GetMapping("/search/{commentId}")
    public ResponseEntity<CommentResponse> searchComment(@PathVariable Long commentId) {

        CommentResponse comment = commentService.findCommentById(commentId);
        return ResponseEntity.ok().body(comment);
    }

    @PutMapping("/update")
    public ResponseEntity<CommentIdResponse> updateComment(
        @RequestBody @Valid CommentUpdateRequest commentUpdateRequest,
        HttpServletRequest httpServletRequest) {

        Long commentId = commentService.updateComment(commentUpdateRequest, httpServletRequest);
        return ResponseEntity.ok().body(new CommentIdResponse(commentId));
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<CommentIdResponse> deleteComment(@PathVariable Long commentId,
        HttpServletRequest httpServletRequest) {

        commentService.deleteComment(commentId, httpServletRequest);
        return ResponseEntity.ok().body(new CommentIdResponse(commentId));
    }
}
