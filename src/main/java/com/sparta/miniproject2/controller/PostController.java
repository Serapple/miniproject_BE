package com.sparta.miniproject2.controller;

import com.sparta.miniproject2.domain.Post;
import com.sparta.miniproject2.dto.PostRequestDto;
import com.sparta.miniproject2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    @PutMapping(value= "/api/auth/post/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, HttpServletRequest request){
        return postService.updatePost(id, postRequestDto, request);
    }
    @DeleteMapping(value = "/api/auth/post/{id}")
    public String deletePost(@PathVariable Long id, HttpServletRequest request){
        return postService.deletePost(id, request);
    }
    @PostMapping(value = "/api/auth/post")
    public String createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.createPost(requestDto, request);
    }
    @GetMapping(value = "/api/post")
    public List<Post> getAllPosts() {
        return postService.getAllPost();
    }

    @GetMapping(value = "/api/post/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }
}

