package com.sparta.miniproject2.service;

import com.sparta.miniproject2.domain.Member;
import com.sparta.miniproject2.domain.Post;
import com.sparta.miniproject2.dto.PostRequestDto;
import com.sparta.miniproject2.jwt.TokenProvider;
import com.sparta.miniproject2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public String updatePost(Long id, PostRequestDto postRequestDto, HttpServletRequest request){
        if (null == request.getHeader("Refresh-Token")) {
            return "로그인이 필요합니다.";
        }

        if (null == request.getHeader("Authorization")) {
            return "로그인이 필요합니다.";
        }
        Member member = validateMember(request);
        if(null == member){
            return "토큰이 유효하지 않습니다.";
        }

        Post post = isPresentPost(id);
        if (post == null) {
            return "존재하지 않는 게시글 id 입니다.";
        }

        if(post.validateMember(member)){
            return "작성자만 수정할 수 있습니다.";
        }

        post.update(postRequestDto);
        return "redirect:/api/post";
    }

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
}
