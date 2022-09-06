package com.sparta.miniproject2.controller;

import com.sparta.miniproject2.domain.Post;
import com.sparta.miniproject2.dto.PostRequestDto;
import com.sparta.miniproject2.dto.UpdateRequestDto;
import com.sparta.miniproject2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
<<<<<<< Updated upstream
import java.util.List;
=======
import java.io.IOException;
>>>>>>> Stashed changes

@RequiredArgsConstructor
//@CrossOrigin(origins = "https://miniproject-nine.vercel.app")
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PostController {

    private final PostService postService;
    @PatchMapping(value= "/api/auth/post/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody UpdateRequestDto requestDto, HttpServletRequest request){
        return postService.updatePost(id, requestDto, request);
    }
    @DeleteMapping(value = "/api/auth/post/{id}")
    public String deletePost(@PathVariable Long id, HttpServletRequest request){
        return postService.deletePost(id, request);
    }
    @PostMapping(value = "/api/auth/post")
<<<<<<< Updated upstream
    public String createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.createPost(requestDto, request);
    }
    @GetMapping(value = "/api/post")
    public List<Post> getAllPosts() {
        return postService.getAllPost();
    }

    @GetMapping(value = "/api/post/{id}")
    public Object getPost(@PathVariable Long id) {
        return postService.getPost(id);
=======
    public String createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request){
        return postService.createPost(postRequestDto, request);
>>>>>>> Stashed changes
    }

//    @PostMapping(value = "/api")
//    public String test(@RequestBody PostRequestDto postRequestDto) {
//        return postService.test(postRequestDto);
//    }
}

