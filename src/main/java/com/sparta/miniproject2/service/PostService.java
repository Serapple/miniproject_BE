package com.sparta.miniproject2.service;

import com.sparta.miniproject2.domain.Member;
import com.sparta.miniproject2.domain.Post;
import com.sparta.miniproject2.dto.PostRequestDto;
<<<<<<< Updated upstream
import com.sparta.miniproject2.dto.UpdateRequestDto;
=======
>>>>>>> Stashed changes
import com.sparta.miniproject2.jwt.TokenProvider;
import com.sparta.miniproject2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Member;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TokenProvider tokenProvider;

    // 게시글 생성
    @Transactional
<<<<<<< Updated upstream
    public String createPost(PostRequestDto requestDto, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return "로그인이 필요합니다.";
        }

        if (null == request.getHeader("Authorization")) {
            return "로그인이 필요합니다.";
        }

        Member member = validateMember(request);
        if (null == member) {
            return "Token이 유효하지 않습니다.";
        }

        Post post = new Post(requestDto, member);
        postRepository.save(post);
        return "redirect:/api/post";
    }

    // 게시글 전체 조회
    @Transactional
    public List<Post> getAllPost()  {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 개별 조회
    @Transactional(readOnly = true)
    public Object getPost(Long id) {
        Post post = isPresentPost(id);
        if (null == post) {
            return "존재하지 않는 게시글 id 입니다.";
        }
        return post;
    }
    
    //게시글 수정
    @Transactional
    public String updatePost(Long id, UpdateRequestDto requestDto, HttpServletRequest request){
=======
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return (Member) tokenProvider.getMemberFromAuthentication();
    }

    @Transactional
    public String createPost(PostRequestDto postRequestDto, HttpServletRequest request){

>>>>>>> Stashed changes
        if (null == request.getHeader("Refresh-Token")) {
            return "로그인이 필요합니다.";
        }

        if (null == request.getHeader("Authorization")) {
            return "로그인이 필요합니다.";
        }
<<<<<<< Updated upstream
        Member member = validateMember(request);
        if(null == member){
            return "토큰이 유효하지 않습니다.";
        }

        Post post = isPresentPost(id);
        if (post == null) {
            return "존재하지 않는 게시글 id 입니다.";
        }
=======

        Member member = validateMember(request);
        if (null == member) {
            return "Token이 유효하지 않습니다.";
        }

//        Post post = new Post(postRequestDto);
//        postRepository.save(post);
>>>>>>> Stashed changes

        if(post.validateMember(member)){
            return "작성자만 수정할 수 있습니다.";
        }

        post.update(requestDto);
        return "redirect:/api/post";
    }

    //게시글 삭제
    @Transactional
    public String deletePost(Long id, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return "로그인이 필요합니다.";
        }

        if (null == request.getHeader("Authorization")) {
            return "로그인이 필요합니다.";
        }

        Member member = validateMember(request);
        if (null == member){
            return "토큰이 유효하지 않습니다.";
        }

        Post post = isPresentPost(id);
        if (null == post) {
            return "존재하지 않는 게시글 id 입니다.";
        }
        if(post.validateMember(member)){
            return "작성자만 삭제할 수 있습니다.";
        }
        postRepository.delete(post);
        return "redirect:/api/post";
    }

<<<<<<< Updated upstream
    @Transactional(readOnly = true)
    public Post isPresentPost(Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }
    
    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }
=======
>>>>>>> Stashed changes
}
