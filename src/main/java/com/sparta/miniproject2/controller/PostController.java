package com.sparta.miniproject2.controller;

import com.sparta.miniproject2.dto.request.PostRequestDto;
import com.sparta.miniproject2.dto.response.ResponseDto;
import com.sparta.miniproject2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
//@CrossOrigin(origins = "https://miniproject-nine.vercel.app")
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PostController {

    private final PostService postService;

    @PutMapping(value = "/api/auth/post/{id}")
    public ResponseDto<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.updatePost(id, requestDto, request);
    }

    @DeleteMapping(value = "/api/auth/post/{id}")
    public ResponseDto<?> deletePost(@PathVariable Long id, HttpServletRequest request) {
        return postService.deletePost(id, request);
    }

    @PostMapping(value = "/api/auth/post")
    public ResponseDto<?> createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.createPost(requestDto, request);
    }

    @GetMapping(value = "/api/post")
    public ResponseDto<?> getAllPosts() {
        return postService.getAllPost();
    }

    @GetMapping(value = "/api/post/{id}")
    public ResponseDto<?> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

}

