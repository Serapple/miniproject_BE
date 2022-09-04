package com.sparta.miniproject2.controller;

import com.sparta.miniproject2.dto.PostRequestDto;
import com.sparta.miniproject2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/api/auth/post")
    public String createPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto);
    }
}
