package com.sparta.miniproject2.controller;

import com.sparta.miniproject2.dto.request.CommentRequestDto;
import com.sparta.miniproject2.dto.response.ResponseDto;
import com.sparta.miniproject2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Validated
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/api/auth/comment")
    public ResponseDto<?> createComment(@RequestBody CommentRequestDto requestDto,
                                        HttpServletRequest request) {
        return commentService.createComment(requestDto, request);
    }

    @DeleteMapping(value = "/api/auth/comment/{id}")
    public ResponseDto<?> deleteComment(@PathVariable Long id,
                                        HttpServletRequest request) {
        return commentService.deleteComment(id, request);
    }

    @GetMapping(value = "/api/comment/{id}")
    public ResponseDto<?> getAllComments(@PathVariable Long id) {

        return commentService.getAllCommentsByPost(id);
    }
}